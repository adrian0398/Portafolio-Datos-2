//
// Created by adrian on 20/8/19.
//

#ifndef SOCKETSERVER_JSON_OUTPUT_H
#define SOCKETSERVER_JSON_OUTPUT_H

#include <string>


typedef struct {
    std::string structtype="";
    std::string val="";


    template<class Archive>
    void serialize(Archive & archive)
    {
        archive( structtype, val); // serialize things by passing them to the archive
    }

} json_out;
#endif //SOCKETSERVER_JSON_OUTPUT_H
