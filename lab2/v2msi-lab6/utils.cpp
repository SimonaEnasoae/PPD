#include"pch.h"
#include"utils.h"
using namespace std;

void write(string file, int **a, int *count, int row) {
  ofstream myfile;
  myfile.open(file);
  for (int i = 0; i < row;i++) {
    for (int j = count[i] - 1; j >= 0; j--) {
      if (i == row - 1 && j == count[i] - 1 && a[i][j] == 0) {
        j--;
      }
      myfile << a[i][j];

    }
  }
  myfile.close();
}
void write2(string file, int **a, int *count, int row) {
  ofstream myfile;
  myfile.open(file);
  for (int i = 0; i < row; i++) {
    for (int j = count[i] - 1; j >= 0; j--) {
      if (i == row - 1 && j == count[i] - 1 && a[i][j] == 0) {
        j--;
      }
     // cout << a[i][j] << " ";
      myfile << a[i][j];

    }
    cout << endl;
  }
  myfile.close();
}