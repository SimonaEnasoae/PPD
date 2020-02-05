#include"pch.h"
#include"V1.h"
using namespace std;
void computeV1(string file, int idProces, int nrProcese) {
  if (idProces == 0) {
    fstream myfile;
    myfile.open(file, fstream::in);
    string line;
    getline(myfile, line);
    int len;
    int tag = 1;
    MPI_Status Stat;
    len = stoi(line);
    int rows = nrProcese + 1;
    int cols = len / (nrProcese - 1) + 2;
    int *count1 = new int[rows];
    int** matrix1 = new int*[rows];

    for (int i = 0; i < rows; ++i)
      matrix1[i] = new int[cols];

    //second number
    int** matrix2 = new int*[rows];

    for (int i = 0; i < rows; ++i)
      matrix2[i] = new int[cols];
    int** result = new int*[rows];

    for (int i = 0; i < rows; ++i)
      result[i] = new int[cols];

    getline(myfile, line);
    int nrOp, rest, start;
    //first number matrix
    nrOp = len / (nrProcese - 1);
    rest = len % (nrProcese - 1);
    start = 0;
    for (int i = 0; i < nrProcese - 1; i++) {
      int finish = start + nrOp;
      if (rest > 0) {
        finish++;
        rest--;
      }
      for (int j = finish - 1; j >= start; j--) {
        matrix1[i][finish - 1 - j] = line[j] - '0';
      }
      count1[i] = finish - start;
      start = finish;
    }
    //second number matrix
    getline(myfile, line);
    nrOp = len / (nrProcese - 1);
    rest = len % (nrProcese - 1);
    start = 0;
    for (int i = 0; i < nrProcese - 1; i++) {
      int finish = start + nrOp;
      if (rest > 0) {
        finish++;
        rest--;
      }
      for (int j = finish - 1; j >= start; j--) {
        matrix2[i][finish - 1 - j] = line[j] - '0';
      }
      start = finish;
    }
    //sending data
    for (int i = 1; i < nrProcese; i++) {
      int tag = 1;
      int dest = i;
      MPI_Send(&count1[i - 1], 1, MPI_INT, dest, tag, MPI_COMM_WORLD);
      MPI_Send(&matrix1[i - 1][0], count1[i - 1], MPI_INT, dest, tag, MPI_COMM_WORLD);
      MPI_Send(&matrix2[i - 1][0], count1[i - 1], MPI_INT, dest, tag, MPI_COMM_WORLD);

    }
    //receving data
    count1[0] += 1;
    for (int i = 1; i < nrProcese; i++) {

      MPI_Recv(&result[i - 1][0], count1[i - 1], MPI_INTEGER, i, tag, MPI_COMM_WORLD, &Stat);
    }
    write("C:/Users/Simona/source/repos/v2msi-lab6/v2msi-lab6/out.txt", result, count1, nrProcese - 1);

    for (int i = 0; i < rows; ++i)
      delete[] matrix1[i];
    delete[] matrix1;
    delete count1;
    myfile.close();
  }
  else {
    int dest = 0;
    int source = 0;
    int nr;
    int tag = 1;
    MPI_Status Stat;
    int transport = 0;
    MPI_Recv(&nr, 1, MPI_INTEGER, source, tag, MPI_COMM_WORLD, &Stat);
    int *a = new int[nr + 1];
    int *b = new int[nr + 1];
    int *r = new int[nr + 2];
    MPI_Recv(&a[0], nr, MPI_INTEGER, source, tag, MPI_COMM_WORLD, &Stat);
    MPI_Recv(&b[0], nr, MPI_INTEGER, source, tag, MPI_COMM_WORLD, &Stat);
    if (idProces != nrProcese - 1) {
      MPI_Recv(&transport, 1, MPI_INTEGER, idProces + 1, tag, MPI_COMM_WORLD, &Stat);

    }
    r[0] = transport;
    for (int i = 0; i < nr; i++) {
      int temp = r[i] + a[i] + b[i];
      r[i] = temp % 10;
      r[i + 1] = temp / 10;
    }
    if (idProces != 1) {
      int transp = r[nr];
      MPI_Send(&transp, 1, MPI_INT, idProces - 1, tag, MPI_COMM_WORLD);
      MPI_Send(&r[0], nr, MPI_INT, dest, tag, MPI_COMM_WORLD);
    }
    else {
      MPI_Send(&r[0], nr + 1, MPI_INT, dest, tag, MPI_COMM_WORLD);
    }

    delete a, b, r;
  }
}