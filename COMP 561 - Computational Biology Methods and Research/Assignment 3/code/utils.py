import os
import math
import gffutils
import numpy as np
from importlib import import_module
from Bio import SeqIO
from Bio.Seq import Seq
from Bio.SeqRecord import SeqRecord


def create_db(gfffn, dbfn):
    if not os.path.exists(dbfn):
        db = gffutils.create_db(data=gfffn, dbfn=dbfn)
    
    return gffutils.FeatureDB(dbfn)


def get_genic_regions(seqfn, db):
    return merge_overlaps(_get_genic_regions(seqfn, db))


def _get_genic_regions(seqfn, db):
    records = SeqIO.parse(open(seqfn), 'fasta')
    genic_regions = []
    offset = 0
    for record in records:
        seqid = record.name

        for feature in db.region(seqid=seqid, featuretype='CDS', strand='+'):
            genic_regions += [ [offset + feature.start, offset + feature.end] ]
        offset += len(record)

    return genic_regions


def get_intergenic_regions(genic_regions, len_seq):
    start, end = 0, 1
    intergenic_regions = []

    if genic_regions[0][start] > 1:
        intergenic_regions.append([
            1,
            genic_regions[0][start] - 1
        ])
    
    for i in range(len(genic_regions) - 1):
        intergenic_regions.append([
            genic_regions[i][end]     + 1,
            genic_regions[i+1][start] - 1
        ])
    
    if genic_regions[-1][end] < len_seq:
        intergenic_regions.append([
            genic_regions[-1][end] + 1,
            len_seq
        ])

    return intergenic_regions


def get_genic_records(seqfn, db):
    start, end = 0, 1

    records = SeqIO.parse(open(seqfn), 'fasta')
    seq = ''.join(str(record.seq) for record in records)
    genic_regions = _get_genic_regions(seqfn, db)

    genic_records = (
        SeqRecord(
            Seq(seq[region[start] - 1 : region[end]]),
            id=f'genic region {i + 1}',
            description=''
        )
        for i, region in enumerate(genic_regions)
    )
    return genic_records


def get_len_regions(regions):
    return sum([end - start + 1 for start, end in regions])


def merge_overlaps(regions):
    n = len(regions)
    start, end = 0, 1
    i = 0
    
    while i < n:
        j = i + 1
        
        while j < n:
            if regions[i][end] + 1 >= regions[j][start]:
                regions[i][end] = regions[j][end]
                regions[j] = None
                
                if j < n - 1:
                    j += 1
                else:
                    i = n
                    break
            else:
                i = j
                break

        if i == n - 1:
            break
    
    return [region for region in regions if region]


def log(x):
    return math.log(x) if x > 0 else float('-inf')


def load_hmm(config):
    config_module = import_module(config.split('.')[0])
    hmm = config_module.HMM()
    return hmm


def traceback(trellis, ptrs, S):
    i = np.argmax(trellis[:,-1])
    L = ptrs.shape[1]
    X = ''
    
    for j in range(L-1, -1, -1):
        X = S[i] + X
        i = ptrs[i, j]
    
    return X


# if __name__ == '__main__':
#     X = ''
#     ptrs = np.array([
#         [0, 0, 0, 0],
#         [0, 0, 0, 0],
#         [0, 0, 0, 0],
#         [0, 0, 0, 0],
#     ])
#     i = 0
#     L = ptrs.shape[1]
#     for j in range(L-1, -1, -1):
#         X = 'A' + X
#         i = ptrs[i, j]
    
#     print(X)