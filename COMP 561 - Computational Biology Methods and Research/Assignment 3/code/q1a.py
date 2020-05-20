# Author: Le Nhat Hung
# McGill ID: 260793376

import os
import Bio.SeqUtils.CodonUsage as cu
from Bio import SeqIO
from utils import (
    create_db,
    get_genic_regions,
    _get_genic_regions,
    get_intergenic_regions,
    get_genic_records,
    get_len_regions
)
from pprint import pprint


class Q1a:
    def __init__(self):
        seqfn = 'Vibrio_cholerae.GFC_11.dna.nonchromosomal.fa'
        gfffn = 'Vibrio_cholerae.GFC_11.37.gff3'
        dbfn = 'Vibrio_cholerae.GFC_11.37.db'
        db = create_db(gfffn, dbfn)

        self.avg_len_genic = get_avg_len_genic(seqfn, db)
        self.avg_len_intergenic = get_avg_len_intergenic(seqfn, db)
        self.nuc_freq_intergenic = get_nuc_freq_intergenic(seqfn, db)
        self.codon_freq_genic = get_codon_freq_genic(seqfn, db)


def main():
    q1a = Q1a()

    print(f'Avg genic region length: {q1a.avg_len_genic:.0f}')
    print(f'Avg intergenic region length: {q1a.avg_len_intergenic:.0f}')

    print('Intergenic nucleotide frequencies:')
    pprint(q1a.nuc_freq_intergenic)

    print('Genic codon frequencies')
    pprint(q1a.codon_freq_genic)


def get_avg_len_genic(seqfn, db):
    genic_regions = _get_genic_regions(seqfn, db)
    return get_len_regions(genic_regions) / len(genic_regions)


def get_avg_len_intergenic(seqfn, db):
    total_len_seqs = sum(len(record) for record in SeqIO.parse(open(seqfn), 'fasta'))
    
    genic_regions = get_genic_regions(seqfn, db)
    num_intergenic_regions = len(genic_regions) - 1

    if genic_regions[0][0] > 1:
        num_intergenic_regions += 1
    
    if genic_regions[-1][1] < total_len_seqs:
        num_intergenic_regions += 1

    len_genic = get_len_regions(genic_regions)
    
    return (total_len_seqs - len_genic) / num_intergenic_regions


def get_nuc_freq_intergenic(seqfn, db):
    nuc_freq = {'A': 0, 'T': 0, 'G': 0, 'C': 0}
    
    records = SeqIO.parse(open(seqfn), 'fasta')
    seq = ''.join(str(record.seq) for record in records)

    intergenic_regions = get_intergenic_regions(
        get_genic_regions(seqfn, db),
        len(seq)
    )
    len_intergenic = get_len_regions(intergenic_regions)

    for nuc in nuc_freq:    
        for start, end in intergenic_regions:
            nuc_freq[nuc] += seq[start - 1: end].count(nuc)
        nuc_freq[nuc] /= len_intergenic        
        nuc_freq[nuc] = round(nuc_freq[nuc], 3)
    
    return nuc_freq


def get_codon_freq_genic(seqfn, db):
    genic_records = get_genic_records(seqfn, db)    
    SeqIO.write(genic_records, 'genic.fa', 'fasta')

    genic_regions = _get_genic_regions(seqfn, db)
    num_genic_codons = get_len_regions(genic_regions) / 3

    cai = cu.CodonAdaptationIndex()
    cai._count_codons('genic.fa')
    codon_freq = cai.codon_count
    
    for codon in codon_freq:
        codon_freq[codon] /= num_genic_codons
        codon_freq[codon] = round(codon_freq[codon], 3)

    os.remove('genic.fa')
    return codon_freq


if __name__ == '__main__':
    main()