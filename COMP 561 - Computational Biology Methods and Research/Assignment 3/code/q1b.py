import sys
import numpy as np
from Bio import SeqIO
from utils import log, load_hmm, traceback
from tqdm import tqdm
from pprint import pprint


def main():
    if len(sys.argv) != 3:
        raise Exception(f'Usage: python {__file__} <fasta> <config file>')
    
    fasta  = sys.argv[1]
    config = sys.argv[2]

    gen_gff(fasta, config, f'{fasta.split(".")[0]}.q1b_out.gff3')


def gen_gff(fasta, config, gfffn):
    records = SeqIO.parse(open(fasta), 'fasta')
    hmm = load_hmm(config)
    # gff_records = []

    open(gfffn, 'w').close()
    open('X.fa', 'w').close()

    for record in records:
        print(f'{record.id}:')
        trellis, ptrs = viterbi(hmm, str(record.seq))
        
        gff_records = gen_gff_records(trellis, ptrs, record, hmm.S)

        with open(gfffn, 'a') as fout:
            for gff_record in gff_records:
                fout.write('\t'.join(gff_record))
                fout.write('\n')

        # with open('X.fa', 'a') as fout:
        #     fout.write(f'>{record.id}\n')
        #     fout.write(f'{traceback(trellis, ptrs, hmm.S)}\n')

    print(f'Saved output in {gfffn}')


def viterbi(hmm, E):
    n = hmm.i.shape[0]
    L = len(E)

    trellis = np.full((n, L), float('-inf'))
    ptrs = np.full((n, L), -1)

    print('Viterbi...')

    for j in tqdm(range(L)):
        for i, s in enumerate(hmm.S):
            if j == 0:
                trellis[i,j] = log(hmm.i[i]) + log(hmm.e[s][E[j]])
                continue

            max_log_prob = float('-inf')
            # max_k = 0
            max_k = -1
            for k, s_prev in enumerate(hmm.S):
                log_prob = trellis[k, max(j - hmm.e_size[s], 0)] \
                         + log(hmm.t[k,i])

                if log_prob > max_log_prob:
                    max_log_prob = log_prob
                    max_k = k

            e = E[j - hmm.e_size[s] + 1 : j + 1]
            trellis[i, j] = max_log_prob + log(hmm.e[s][e])

            ptrs[i, j] = max_k
            
            # if max_k == hmm.S.index('S') or max_k == hmm.S.index('T'):
            #     ptrs[max_k, j - hmm.e_size[hmm.S[max_k]] + 1: j] = max_k
                
    return (trellis, ptrs)


def gen_gff_records(trellis, ptrs, seq_record, S):
    gff_records = []

    in_genic = False
    i = np.argmax(trellis[:,-1])

    print('Predicting genes...')
    
    for j in tqdm(range(ptrs.shape[1] - 1, -1, -1)):
        if S[i] == 'T' and not in_genic:
            end = j + 1
            in_genic = True
        
        elif S[i] == 'S' and in_genic:
            # start = j - 2 + 1
            start = j - 3
            in_genic = False
            gff_records = [[seq_record.id, 'ena', 'CDS', str(start), str(end), '.', '+', '0', '.']] + gff_records
        
        i = ptrs[i, j]
    
    print()
    return gff_records


if __name__ == '__main__':
    main()