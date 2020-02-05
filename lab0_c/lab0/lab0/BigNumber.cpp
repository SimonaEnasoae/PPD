#include"BigNumber.h"
#include"pch.h"
std::string BigNumber::getNumber() {
  int index;
  std::string s = "";
  for (index = 0; index < size; index++) {
    std::cout << v[index];
    s += std::to_string(v[index]);
  }
  std::cout << std::endl;
  return s;

}
