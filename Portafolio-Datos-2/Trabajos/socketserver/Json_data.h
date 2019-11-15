//
// Created by adrian on 20/8/19.
//

#ifndef SOCKETSERVER_JSON_DATA_H
#define SOCKETSERVER_JSON_DATA_H


#include <string>


typedef struct {
    std::string data_type1="";
    std::string data_type2="";
    std::string value ="";
    std::string value2="";

    template<class Archive>
    void serialize(Archive & archive)
    {
        archive( data_type1, data_type2, value, value2); // serialize things by passing them to the archive
    }

} json_data;

#endif //SOCKETSERVER_JSON_DATA_H
