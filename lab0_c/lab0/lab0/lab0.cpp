#include "pch.h"
#include <conio.h>


int main()
{
  write(2, L"hello 111");
  generateNumbersToFile("test.txt", 2, 100, 150);
  bool var =compareFiles("C:\\Users\\Simona\\Desktop\\An 3\\PPD\\labs\\c++\\lab0\\lab0\\test1.txt", "C:\\Users\\Simona\\Desktop\\An 3\\PPD\\labs\\c++\\lab0\\lab0\\test2.txt");
  std::cout << var;
  return 0;
}
