# NER Application

Este es un proyecto que proporciona una aplicación para el reconocimiento de entidades nombradas (NER) utilizando Stanford CoreNLP.

## Descripción

La NER Application es una aplicación basada en Java que utiliza la biblioteca Stanford CoreNLP para identificar y extraer entidades nombradas de texto. Las entidades nombradas pueden incluir nombres de personas, organizaciones, ubicaciones, fechas, cantidades, etc. Esta aplicación ofrece un servicio RESTful que acepta texto de entrada y devuelve las entidades nombradas encontradas en el texto.

## Funcionalidades

- Reconocimiento de entidades nombradas (NER) en texto.
- Servicio RESTful para acceder al reconocimiento de entidades nombradas.

## Requisitos

- Java 8 o superior
- Maven (para compilar y ejecutar el proyecto)

## Uso

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE favorito.
3. Compila el proyecto utilizando Maven.
4. Ejecuta la aplicación.
5. Envía solicitudes REST para realizar el reconocimiento de entidades nombradas.

## Ejemplo de solicitud REST

http
POST /api/v1/ner?type=PERSON HTTP/1.1
Content-Type: application/json

{
  "input": "El presidente Barack Obama vive en Washington D.C."
}
