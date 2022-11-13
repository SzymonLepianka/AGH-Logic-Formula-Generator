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

### 3.1 Pierwszy algorytm (*Labelling pattern expressions*)

Pierwszy algorytm jest odpowiedzialny za nadawanie oznaczeń wzorcom, umożliwia rozpoznawanie, które argumenty należą do 
poszczególnych wzorców w podanym wyrażeniu.
Przykładowo, po użyciu pierwszego algorytmu na wyrażeniu:

```w = Seq(a, Seq(ConcurRE(b, c, d), ConcurRE(e, f, g)))```,

otrzymamy wyrażenie oznakowane

```w' = Seq(1]a, Seq(2]ConcurRE(3]b, c, d[3), ConcurRE(3]e, f, g[3)[2)[1)```.

Tak wyrażenie z nadanymi indeksami można poddać następnie dalszej analizie.

### 3.2 Drugi algorytm (*Calculating consolidated expression*)
Drugi algorytm jest odpowiedzialny za generowanie skonsolidowanego wyrażenia. 

Argumenty algorytmu:
- analizowane wyrażenie (w), np. ``Seq(2]ConcurRE(3]b,c,d[3]), ConcurRE(3]e,f,g[3])[2)``, 
- typ wyrażenia (``t = ini`` lub ``t = fin``)
- predefined pattern property set(Σ) - czyli zbiór wzorców, które dopuszczalne są podczas generowania wyrażenia logicznego.

Weźmy podane wyżej wyrażenie:

```Seq(2]Concur(3]b, c, d[3), ConcurRE(3]e, f, g[3)[2)```.

Przepuszczając je przez drugi algorytm, wykonane zostaną poniższe czynności:
1. Sprawdzenie jaki typ wyrażenia jest akurat sprawdzany oraz przypisanie
   do wartości ``ex`` odpowiednio wartości ``ini`` lub ``fin``, która została odczytana
   z *Pattern Property Set*.
2. Ustawienie wartości type zmiennej ``argType``
3. Następnie wykonywana jest pętla po wszystkich argumentach ``a`` w wyrażeniu w dla których typ ``a`` jest równy wartości zmiennej ``argType``
4. Jeśli ``a`` nie jest atomiczna to podmieniane jest ``a`` w wyrażeniu ``ex`` przez
   wartość funkcji ``ConsEx(a, t, Σ)``
5. Po przejściu pętli zwracane jest skonsolidowane wyrażenie ``ex``, które dla
   podanego wyżej przykładu będzie symbolem ``g`` (fin) oraz ``a`` (ini).


