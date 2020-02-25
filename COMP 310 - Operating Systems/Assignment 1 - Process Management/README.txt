COMPILATION INSTRUCTION:

FORK version: make -B fork
VFORK version: make -B vfork
CLONE version: make -B clone
FIFO version: make -B fifo

FIFO INSTRUCTION:

First, create FIFO by typing in regular shell (not tiny shell):
  mkfifo FIFO_NAME

Launch tiny shell which writes into FIFO with:
  ./tshell PIPE_NAME 1

Launch tiny shell which reads from FIFO with:
  ./tshell PIPE_NAME 0
