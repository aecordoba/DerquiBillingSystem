#!/bin/bash

if [ "$#" -eq 0 ]; then
    java -jar bin/DerquiBillingSystemUserInterface-3.1.0.jar
elif [ "$#" -eq 1 ]; then
    java -jar bin/DerquiBillingSystemUserInterface-3.1.0.jar "$1"
elif [ "$#" -eq 2 ]; then
    java -jar bin/DerquiBillingSystemUserInterface-3.1.0.jar "$1" "$2"
else
    echo "Illegal number of parameters"
    echo "USO: billing [\"<directorio_archivos_ama>\" [\"<nombre_archivo_salida>\"]]"
    exit 1
fi
