# Signature Generator

Sistema interno para geração e gestão de assinaturas de e-mail dos colaboradores.

A aplicação permite selecionar um colaborador e gerar a assinatura de e-mail correspondente, pronta para ser copiada e utilizada no cliente de e-mail.

## Tecnologias

* Java 21
* Spring Boot
* Maven
* Google Sheets API

### Dados

Os dados dos colaboradores são mantidos numa folha do Google Sheets e consumidos pelo backend através da Google Sheets API.

## Arquitetura

```text
Frontend (Next.js)
        │
        │ HTTP
        ▼
Backend (Spring Boot)
        │
        │ Google Sheets API
        ▼
Google Sheets
```

## Funcionalidades

* Listagem de colaboradores
* Seleção de colaborador
* Pré-visualização da assinatura
* Geração dinâmica da assinatura em HTML
* Cópia da assinatura para a área de transferência
* Integração com Google Sheets
* API REST para consulta dos colaboradores
* Tratamento global de erros da API

## API

### Listar colaboradores

```http
GET /api/v1/employees
```

Retorna todos os colaboradores disponíveis.

### Consultar colaborador

```http
GET /api/v1/employees/{id}
```

Retorna um colaborador pelo seu ID.

Exemplo:

```http
GET /api/v1/employees/1
```

### Respostas de erro

Quando um colaborador não é encontrado:

```json
{
  "status": 404,
  "message": "Funcionário não encontrado."
}
```

Erros internos da aplicação retornam:

```json
{
  "status": 500,
  "message": "Ocorreu um erro interno no servidor."
}
```
