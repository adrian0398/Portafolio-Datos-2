cmake_minimum_required(VERSION 3.14)
project(socketserver)

set(CMAKE_CXX_STANDARD 14)

include_directories(./include)

add_executable(socketserver main.cpp Json_data.h binarytree.h linkedlist.h Json_output.h)