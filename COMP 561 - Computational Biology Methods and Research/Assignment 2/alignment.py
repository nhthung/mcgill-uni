# Author: Le Nhat Hung
# ID    : 260793376

import sys, math
import utils

sys.setrecursionlimit(100000)


def main():
    match_score    =  1
    mismatch_score = -1
    b              = -1
    filenames = (
        'hw1_short.fa',
        'hw1_medium.fa',
        'hw1_long.fa',
    )
    for filname in filenames:
        multi_gap_free(filname, match_score, mismatch_score, b)


def multi_gap_free(filename, match_score, mismatch_score, b):
    # Substitution cost matrix
    s = lambda Sj, Ti : match_score if Sj == Ti else mismatch_score

    # Load FASTA sequences
    seqs = utils.load_fa(filename)

    # Take shorter seq as S, longer as T
    S, T = seqs['S']['seq'], seqs['T']['seq']

    # Align and score
    M, S_gap, T_gap, back_ptrs = _multi_gap_free(S, T, s, b)
    alignments = traceback(M, S_gap, T_gap, back_ptrs, S, T, find_all=False)
    score = utils.score_alignment(M, S_gap, T_gap)

    utils.print_alignments(alignments, seqs, filename, score)


def _multi_gap_free(S, T, s, b):
    '''
    :param S: Sequence spanning columns (top)
    :param T: Sequence spanning rows (left)
    :param s: Substitution cost matrix
    :param b: Gap penalty cost
    '''
    m, n = len(S), len(T)

    if not n >= m >= math.ceil(n/2):
        err_msg = 'Lengths m, n of S, T need to fulfill n >= m >= ceil(n/2)'
        raise ValueError(err_msg)
    
    M         = utils.init_M(m, n)
    S_gap     = utils.init_S_gap(m, n, b)
    T_gap     = utils.init_T_gap(m, n, b)
    back_ptrs = utils.init_back_ptrs(m, n)

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            Ti = T[i-1]
            Sj = S[j-1]

            utils.fill_M(M, S_gap, T_gap, back_ptrs, Ti, Sj, i, j, s)
            utils.fill_S_gap(M, S_gap, T_gap, back_ptrs, i, j, b)
            utils.fill_T_gap(M, S_gap, T_gap, back_ptrs, i, j, b)
    
    return M, S_gap, T_gap, back_ptrs


def traceback(M, S_gap, T_gap, back_ptrs, S, T, find_all=False):
    alignments = []
    n, m = len(T), len(S)

    start_tables = utils.get_start_tables(M, S_gap, T_gap, S, T)

    for start_table in start_tables:
        if start_table == 'M':
            alignment = [S[-1], T[-1]]
            _S = S[:-1]
            _T = T[:-1]

        elif start_table == 'S_gap':
            alignment = ['-', T[-1]]
            _S = S
            _T = T[:-1]
            
        elif start_table == 'T_gap':
            alignment = [S[-1], '-']
            _S = S[:-1]
            _T = T

        new_alignments = _traceback(back_ptrs, start_table, n, m, _S, _T, alignment, alignments, find_all=find_all)
        if new_alignments:
            alignments += new_alignments

        if not find_all:
            break

    return alignments


def _traceback(back_ptrs, ptrs_table, i, j, S, T, alignment, alignments, find_all=False):
    '''
    Perform traceback for find alignments

    :param find_all:
    '''
    ptrs = back_ptrs[ptrs_table][i][j]

    back_ij = {
        'M'    : (i-1, j-1),
        'S_gap': (i-1, j),
        'T_gap': (i, j-1),
    }
    back_i, back_j = back_ij[ptrs_table]

    if i == 0 or j == 0:
        if find_all:
            alignments.append(alignment)
            return alignments
        else:
            return [alignment]
    else:
        if find_all:
            try:
                if 'M' in ptrs:
                    _traceback(back_ptrs, 'M', back_i, back_j, S[:-1], T[:-1], [S[-1] + alignment[0], T[-1] + alignment[1]], alignments, find_all)
                
                if 'S_gap' in ptrs:
                    _traceback(back_ptrs, 'S_gap', back_i, back_j, S, T[:-1], ['-' + alignment[0], T[-1] + alignment[1]], alignments, find_all)
                
                if 'T_gap' in ptrs:
                    _traceback(back_ptrs, 'T_gap', back_i, back_j, S[:-1], T, [S[-1] + alignment[0], '-' + alignment[1]], alignments, find_all)
            except:
                alignments.append(alignment)
                return alignments
        else:
            try:
                if 'M' in ptrs:
                    return _traceback(back_ptrs, 'M', back_i, back_j, S[:-1], T[:-1], [S[-1] + alignment[0], T[-1] + alignment[1]], alignments, find_all)
                
                if 'S_gap' in ptrs:
                    return _traceback(back_ptrs, 'S_gap', back_i, back_j, S, T[:-1], ['-' + alignment[0], T[-1] + alignment[1]], alignments, find_all)
                
                if 'T_gap' in ptrs:
                    return _traceback(back_ptrs, 'T_gap', back_i, back_j, S[:-1], T, [S[-1] + alignment[0], '-' + alignment[1]], alignments, find_all)
            except:
                return [alignment]


if __name__ == '__main__':
    main()