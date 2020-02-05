#include"pch.h"
#include"V2.h"
using namespace std;
void computeV2(string file, int idProces, int nrProcese) {
  char *mystring1;
  char *mystring2;
  char stringLen[100];
  int nrDigits;
  FILE * pFile;
  fopen_s(&pFile, file.c_str(), "rb");
  fgets(stringLen, 100, pFile);
  int nr = stoi(stringLen);
  nrDigits = nr;
  mystring1 = new char[nr + 1];
  mystring2 = new char[nr + 1];
  int start = strlen(stringLen);
  fseek(pFile, start, SEEK_SET);
  fseek(pFile, nr / nrProcese * idProces, SEEK_CUR);

  if (idProces != nrProcese - 1) {
   

    fgets(mystring1, nr / nrProcese + 1, pFile);


    fseek(pFile, start, SEEK_SET);
    fseek(pFile, nr + 2 + nr / nrProcese * idProces, SEEK_CUR);

    fgets(mystring2, nr / nrProcese + 1, pFile);


    fclose(pFile);
  }
  else {
   
    if (nr%nrProcese == 0) {
      fgets(mystring1, (nr / nrProcese + 1), pFile);
    }
    else {
      fgets(mystring1, (nr / nrProcese + nr % nrProcese + 1), pFile);
    }

    fseek(pFile, start, SEEK_SET);


    fseek(pFile, nr + 2 + nr / nrProcese * idProces, SEEK_CUR);
    if (nr%nrProcese == 0) {
      fgets(mystring2, nr / nrProcese + 1, pFile);
    }
    else {
      fgets(mystring2, nr / nrProcese + nr % nrProcese + 1, pFile);
    }


    fclose(pFile);
  }
  int len = strlen(mystring1);
  int *a = new int[len + 1];
  int *b = new int[len + 1];
  int *r = new int[len + 2];
  int tag = 1;
  MPI_Status Stat;
  int dest = 0;
  for (int i = len - 1; i >= 0; i--) {
    a[len - i - 1] = mystring1[i] - '0';
  }
  for (int i = len - 1; i >= 0; i--) {
    b[len - i - 1] = mystring2[i] - '0';
  }
  int transport = 0;
  if (idProces != nrProcese - 1) {
    MPI_Recv(&transport, 1, MPI_INTEGER, idProces + 1, tag, MPI_COMM_WORLD, &Stat);

  }
  r[0] = transport;
  for (int i = 0; i < len; i++) {
    int temp = r[i] + a[i] + b[i];
    r[i] = temp % 10;
    r[i + 1] = temp / 10;
  }

  if (idProces > 0) {
    int transp = r[len];
    MPI_Send(&transp, 1, MPI_INT, idProces - 1, tag, MPI_COMM_WORLD);
    MPI_Send(&len, 1, MPI_INT, dest, tag, MPI_COMM_WORLD);
    MPI_Send(&r[0], len, MPI_INT, dest, tag, MPI_COMM_WORLD);
  }
  else {
    int rows = nrProcese + 1;
    int counter;
    int cols = nrDigits / nrProcese + nrDigits % nrProcese + 1;
    int** result = new int*[rows];

    for (int i = 0; i < rows; ++i)
      result[i] = new int[cols];
    int *count = new int[rows];
    count[0] = len + 1;

    for (int i = 1; i < nrProcese; i++) {
      MPI_Recv(&counter, 1, MPI_INTEGER, i, tag, MPI_COMM_WORLD, &Stat);
      count[i] = counter;
      MPI_Recv(&result[i][0], counter, MPI_INTEGER, i, tag, MPI_COMM_WORLD, &Stat);
    }
    for (int i = 0; i < count[0]; i++) {
      result[0][i] = r[i];
    }

    write2("C:/Users/Simona/source/repos/v2msi-lab6/v2msi-lab6/out2.txt", result, count, nrProcese);

  }

  delete a, b, r;
  delete mystring1;
  delete mystring2;
}