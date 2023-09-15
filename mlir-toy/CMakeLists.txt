cmake_minimum_required(VERSION 3.13.4)

project(mlirtoy) # Name of the project

# Include MLIR As a dependency for this project
set(MLIR_INSTALL_DIR "/home/abhishek/GitHub/llvm/build" # Set the MLIR BUILD Directory
        CACHE PATH "MLIR Inst. DIR")
list(APPEND CMAKE_PREFIX_PATH                           # Tell CMAKE where to search for MLIRConfig.cmake in this dir
        ${MLIR_INSTALL_DIR}/lib/cmake/mlir)  
find_package(MLIR REQUIRED CONFIG)                      # Find the MLIRConfig.cmake             

set(LLVM_RUNTIME_OUTPUT_INTDIR ${CMAKE_BINARY_DIR}/bin)
set(LLVM_LIBRARY_OUTPUT_INTDIR ${CMAKE_BINARY_DIR}/lib)
set(MLIR_BINARY_DIR ${CMAKE_BINARY_DIR})

set(LLVM_LINK_COMPONENTS
  Support
)


list(APPEND CMAKE_MODULE_PATH "${MLIR_CMAKE_DIR}") # CMAKE searches in This PATH for cmake modules when using
list(APPEND CMAKE_MODULE_PATH "${LLVM_CMAKE_DIR}") # find_package or include

if(POLICY CMP0116)
  cmake_policy(SET CMP0116 OLD)
endif()

include(TableGen)
include(AddLLVM)
include(AddMLIR)
include(HandleLLVMOptions) 

include_directories(include/)
include_directories(${CMAKE_CURRENT_BINARY_DIR}/include/)
include_directories(/home/abhishek/GitHub/Kaleidoscope/mlir-toy/build/include)
include_directories(${LLVM_INCLUDE_DIRS})
include_directories(${MLIR_INCLUDE_DIRS})
include_directories(${PROJECT_SOURCE_DIR}/include)
include_directories(${PROJECT_BINARY_DIR}/include)
link_directories(${LLVM_BUILD_LIBRARY_DIR})
link_directories(/home/abhishek/GitHub/llvm/build/lib)
add_definitions(${LLVM_DEFINITIONS})

add_subdirectory(include/)


set(CMAKE_CXX_STANDARD 17 CACHE STRING "")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fno-rtti")

add_executable(mlirtoy 
toyc.cpp
toyc.cpp
parser/AST.cpp
mlir/MLIRGen.cpp
mlir/Dialect.cpp
)

add_dependencies(mlirtoy ToyDep)


target_link_libraries(mlirtoy
  PRIVATE
    MLIRAnalysis
    MLIRIR
    MLIRParser
    MLIRSideEffectInterfaces
    MLIRTransforms
    LLVMCore
    LLVMSupport
)



# set(LLVM_INSTALL_DIR "/home/abhishek/GitHub/llvm/build" 
#         CACHE PATH "LLVM Inst. DIR")
# list(APPEND CMAKE_PREFIX_PATH
#         ${LLVM_INSTALL_DIR}/lib/cmake/llvm)
# find_package(LLVM REQUIRED CONFIG)
# include_directories(${LLVM_INCLUDE_DIRS})
# link_directories(${LLVM_LIBRARY_DIRS})
# add_definitions(${LLVM_DEFINITIONS})