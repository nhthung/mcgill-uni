import numpy as np
from collections import defaultdict
from q1a import Q1a


class HMM:
    def __init__(self):
        self.S = ['I', 'S', 'G', 'T']
        self.e_size = {
            'I': 1,
            'S': 3,
            'G': 3,
            'T': 3
        }
        self.e = get_e()
        self.i = np.array([1,0,0,0])
        self.t = get_t()


start_codon = 'ATG'
stop_codons = ['TAG', 'TAA', 'TGA']
q1a = Q1a()


def get_e():
    e = {
        'I': q1a.nuc_freq_intergenic,
        'S': defaultdict(int, {start_codon: 1}),
        'G': defaultdict(float, q1a.codon_freq_genic),
        'T': defaultdict(float, ((codon, 1/len(stop_codons)) for codon in stop_codons)),
    }
    return e


def get_t():
    avg_len_intergenic = round(q1a.avg_len_intergenic)
    avg_len_genic = round(q1a.avg_len_genic/3)
    t = np.array([
        [(avg_len_intergenic - 1)/avg_len_intergenic, 1/avg_len_intergenic, 0, 0],
        [0, 0, 1, 0],
        [0, 0, (avg_len_genic - 1)/avg_len_genic, 1/avg_len_genic],
        [1, 0, 0, 0]
    ])
    return t