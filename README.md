# AGH-Logic-Formula-Generator

Logic Formula Generator implementation for SP2 course on AGH UST.

## 1. Założenie projektu

Głównym założeniem projektu jest implementacja algorytmów odpowiedzialnych za generowanie formuł logicznych dla logiki
pierwszego rzędu na podstawie artykułu *Pattern-based and composition-driven automatic generation of logical
specifications for workflow-oriented software models* (https://doi.org/10.1016/j.jlamp.2019.02.005).

## 2. Wymagania

- Implementacja generatora formuł logicznych w języku Java
- Translacja wzorców z oryginalnej logiki na logikę pierwszego rzędu
- Testy programu

## 3. Opis przygotowanego projektu

Zaimplementowano 3 algorytmy:

### 3.1 Pierwszy algorytm

Pierwszy algorytm jest odpowiedzialny za nadawanie oznaczeń wzorcom, umożliwia rozpoznawanie, które argumenty należą do 
poszczególnych wzorców w podanym wyrażeniu.
Przykładowo, po użyciu pierwszego algorytmu na wyrażeniu:

```w = Seq(a, Seq(ConcurRE(b, c, d), ConcurRE(e, f, g)))```,

otrzymamy wyrażenie oznakowane

```w' = Seq(1]a, Seq(2]ConcurRE(3]b, c, d[3), ConcurRE(3]e, f, g[3)[2)[1)```

Tak wyrażenie z nadanymi indeksami można poddać następnie dalszej analizie.


