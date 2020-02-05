#pragma once
#include <iostream>
#include <fstream>
#include <string>
#include <windows.h>
#include"BigNumber.h"
#include "libxl.h"

bool compareFiles(std::string fileName1, std::string fileName2);
void write(int row, const wchar_t *string);
void generateNumbersToFile(std::string fileName, int size, int min, int max);
