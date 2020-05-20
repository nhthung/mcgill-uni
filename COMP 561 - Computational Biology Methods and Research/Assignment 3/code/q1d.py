from tqdm import tqdm
from utils import create_db
from pprint import pprint


def main():
    db_pred = create_db('Vibrio_vulnificus.q1b_out.gff3', 'Vibrio_vulnificus.q1b_out.db')
    db_anno = create_db('Vibrio_vulnificus.ASM74310v1.37.gff3', 'Vibrio_vulnificus.ASM74310v1.37.db')

    print('Comparing annotated genes to predicted genes...')
    matches_anno = compare(db_anno, db_pred)

    print('Comparing predicted genes to annotated genes...')
    matches_pred = compare(db_pred, db_anno)

    print()

    genes = ('annotated', 'predicted')
    pretty = {
        'both': 'perfectly match both ends of',
        'start': 'match the start but not the end of',
        'end': 'match the end but not the start of',
        'neither': 'don\'t match neither the start nor end of'
    }
    for i, (matches, gene) in enumerate(zip((matches_anno, matches_pred), genes)):
        print(f'{gene.capitalize()} genes that')
        for k, v in matches.items():
            print(f'{pretty[k].capitalize()} {"a" if i == 0 else "an"} {genes[abs(1-i)]} gene: {v*100:.2f}%')
        print()


def get_seqids(features):
    seqids = []
    for feature in features:
        if feature.seqid not in seqids:
            seqids.append(feature.seqid)
    return seqids


def compare(db1, db2):
    db1_features = (feature for feature in db1.features_of_type('CDS') if feature.strand == '+')
    seqids = get_seqids(db1_features)
    num_features = 0

    matches = {k: 0 for k in ('both', 'start', 'end', 'neither')}

    for seqid in tqdm(seqids):
        for db1_feature in db1.region(seqid=seqid, featuretype='CDS', strand='+'):
            num_features += 1
            has_match = False

            for db2_feature in db2.region(seqid=seqid, featuretype='CDS', strand='+'):
                if db1_feature.start == db2_feature.start \
                        and db1_feature.end == db2_feature.end:
                    matches['both'] += 1
                    has_match = True
                    break
            
            if has_match:
                continue

            for db2_feature in db2.region(seqid=seqid, featuretype='CDS', strand='+'):
                if db1_feature.start == db2_feature.start:
                    matches['start'] += 1
                    has_match = True
                    break

                elif db1_feature.end == db2_feature.end:
                    matches['end'] += 1
                    has_match = True
                    break
            
            if not has_match:
                matches['neither'] += 1
            
    
    for k, v in matches.items():
        matches[k] /= num_features
    
    return matches


if __name__ == '__main__':
    main()