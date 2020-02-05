#pragma once
#include<string>
class BigNumber
{
private:
  int v[100];
  int size;
public:
  std::string getNumber();
  BigNumber() = default;
  BigNumber(int v1[], int size1)
  {
    for(int i = 0; i < size1; i++){
      v[i] = v1[i];
    }
    size = size1;
  }

  int getSize()
  {
    return size;
  }
  int* getV()
  {
    return v;
  }
};