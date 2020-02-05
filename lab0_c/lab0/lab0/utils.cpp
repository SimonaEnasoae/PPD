#include "utils.h"
#include"pch.h"
using namespace libxl;
/**
* write in a xlxs file on the rowTable row the string
* @param rowTabel the row of the file
* @param data the string
* @throws IOException if the file doesn't exist
*/
void write(int row, const wchar_t *string) {
  Book* book = xlCreateBook();
  if (book)
  {
    if (book->load(L"example.xls"))
    {
      Sheet* sheet = book->getSheet(0);
      if (sheet)
      {

        sheet->writeStr(row, 0, string);
      }
    }
    else
    {
      std::cout << "At first run generate !" << std::endl;
    }
    if (!book->save(L"example.xls"))
    {
      std::cout << book->errorMessage() << std::endl;
    }

    book->release();
  }
}

/**
* write generated numbers in a file
*
* @param fileName the name of file
* @param size how many numbers to be generated
* @param min the minimum number
* @param max the maximum number
*/
void generateNumbersToFile(std::string fileName, int size, int min, int max) {
  std::ofstream file;
  file.open(fileName, std::fstream::out);

  for (int counter = size; counter > 0; counter--) {
    int lenght = min + rand() % (max - min);
    int number = 1 + rand() % 9;
    file<<number;
    for (int index = 0; index < lenght - 1; index++) {
      number = rand() % 10;
      file<<number;
    }
    file << "\n";

  }
  file.close();
 
}
/**
* compare 2 files, true if the files have the same content, false otherwise
* @param fileName1 the name of the first file
* @param fileName2 the name of the second file
* @return
*/
bool compareFiles(std::string fileName1, std::string fileName2) {
  std::ifstream myfile1;
  myfile1.open(fileName1);
  std::ifstream myfile2;
  myfile2.open(fileName2);
  std::string line1, line2;
  while (std::getline(myfile1, line1))
  {
    if (std::getline(myfile2, line2)) {
      if (line1.compare(line2) != 0) {
        return false;
      }
    }
    else {
      return false;
    }
  }
  if (std::getline(myfile2, line2)) {
    return false;
  }
  myfile1.close();
  myfile2.close();
  return true;
}
