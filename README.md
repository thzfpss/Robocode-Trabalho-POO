# Projeto RoboCode

Este repositório contém o código-fonte dos robôs desenvolvidos para a competição de Programação Orientada a Objetos (POO).

## Grupo
* Samuel Diogo  
* Caio Henrique
* Matheus Henry


## Estratégia de Código (POO)
Utilizamos o conceito de **Herança** para evitar repetição de código e implementar a segurança do time (evitar Fogo Amigo).
* **`RoboBase` (Classe Pai):** Contém a lógica comum, cores do time e o método `atirarComSeguranca()`, que impede que um robô atire no parceiro.
* **`Facada` (Atacante):** Herda da RoboBase. Possui lógica de **Mira Preditiva** (calcula onde o inimigo estará) e movimentação agressiva.
* **`Locking` (Sobrevivente):** Herda da RoboBase. Possui lógica **Anti-Ram** (foge de colisões) e mantém distância para garantir pontos de sobrevivência.
