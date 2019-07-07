igblastn -query /media/ephemeral0/applicator/loquat/output/consensus/consensus.fasta \
  -germline_db_V /media/ephemeral0/ohnosequences/db-tcr-alphabeta/0.4.0/9606.TRB.V/blast/9606.TRB.V \
  -germline_db_D /media/ephemeral0/ohnosequences/db-tcr-alphabeta/0.4.0/9606.TRB.D/blast/9606.TRB.D \
  -germline_db_J /media/ephemeral0/ohnosequences/db-tcr-alphabeta/0.4.0/9606.TRB.J/blast/9606.TRB.J \
  -ig_seqtype TCR -organism human \
  -clonotype_out /media/ephemeral0/applicator/loquat/output/clonotypes_out.txt \
  -out /dev/null -num_threads 1 -evalue 10 -max_target_seqs 500 -strand both -word_size 11 \
  -penalty -3 -reward 2 \
  -auxiliary_data /media/ephemeral0/ohnosequences/db-tcr-alphabeta/0.4.0/9606.TRB.J/blast/9606.TRB.J.aux \
  -num_alignments_V 3 -num_alignments_D 3 -num_alignments_J 3 -min_V_length 150 -min_J_length 15 \
  -min_D_match 5 -D_penalty -4 -J_penalty -3 -extend_align5end -num_clonotype 500000 -domain_system imgt -outfmt 7

