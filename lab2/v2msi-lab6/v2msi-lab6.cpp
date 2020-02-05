// v2msi-lab6.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
using namespace std;

int main(int argc, char** argv) {
  // Initialize the MPI environment
  MPI_Init(&argc, &argv);

  // Get the number of processes
  int world_size;
  MPI_Comm_size(MPI_COMM_WORLD, &world_size);

  // Get the rank of the process
  int world_rank;
  MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

  // Get the name of the processor
  char processor_name[MPI_MAX_PROCESSOR_NAME];
  int name_len;
  MPI_Get_processor_name(processor_name, &name_len);

  //fct(world_rank,world_size);

  computeV2("C:/Users/Simona/source/repos/v2msi-lab6/v2msi-lab6/test.txt",world_rank,world_size);
  //computeV1("C:/Users/Simona/source/repos/v2msi-lab6/v2msi-lab6/test.txt",world_rank,world_size);
  // Print off a hello world message
  

  // Finalize the MPI environment.
  MPI_Finalize();

}



