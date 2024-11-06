*Descrição do Projeto*

Este programa em Java simula o gerenciamento de rendimentos mensais de um trabalhador com contratos de trabalho variáveis. O sistema permite cadastrar um trabalhador, seu departamento, nível de experiência, salário base, e adicionar múltiplos contratos de trabalho associados. Com isso, ele possibilita calcular a renda mensal com base no salário e nos valores de contratos no período desejado.

*Funcionalidades Principais*

Cadastro do Trabalhador: Armazena dados do trabalhador, incluindo nome, nível de experiência (WorkerLevel), salário base e o departamento ao qual pertence.
Contratos de Trabalho: Permite registrar contratos individuais com dados de data, valor por hora e quantidade de horas trabalhadas.
Cálculo de Rendimento: Calcula o rendimento mensal do trabalhador somando o salário base ao valor dos contratos realizados no mês especificado.
Tratamento de Exceções: Valida entradas de dados, como formatos de data e valores, com mensagens de erro para entradas inválidas.

*Estrutura do Código*
O programa inclui três classes principais:

Worker: Representa o trabalhador e contém uma lista de HourContract.
HourContract: Representa os contratos do trabalhador, com informações sobre o valor por hora, data e duração.
Department: Armazena o nome do departamento do trabalhador.
Como Usar
Insira as informações do trabalhador, como nome, nível e salário base.
Adicione contratos com data, valor por hora e duração em horas.
Calcule o rendimento mensal ao informar o mês e ano desejado.
Este projeto é ideal para simular cenários de rendimento em ambientes de contratação por hora ou folha de pagamento variável.
