#include <stdio.h>
#include <stdbool.h>

// Esse codigo verifica se o caracter do char C � uma vogal, sendo ela maiscula ou minuscula

bool doidao (char c) {
    bool resp = false;
    int v = (int) c;

    if (v == 65 || v == 69 || v == 73 || v == 79 || v == 85 ||
        v == 97 || v == 101 || v == 105 || v == 111 || v == 117) {
        resp = true;
    }

    return resp;
}
