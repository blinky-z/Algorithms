# O-нотация

**Содержание:**
- [Описание](#описание)
    - [O-нотация и алгоритмы](#o-нотация-и-алгоритмы)
    - [Отбрасывание non-dominant слагаемых](#отбрасывание-non-dominant-слагаемых)
    - [Логарифм в асимптотике](#логарифм-в-асимптотике)
- [Omega-O](#omega-o)
    - [Формальное определение](#формальное-определение)
- [Theta-O. Asymptotically Tight Bound](#theta-o-asymptotically-tight-bound)
    - [Формальное определение](#формальное-определение-1)
- [Big-O](#big-o)
    - [Формальное определение](#формальное-определение-2)
- [Sharp bound](#sharp-bound)
- [Little-O, Little-Omega](#little-o-little-omega)
- [Пределы](#пределы)
- [Как произносить асимптотические оценки на английском](#как-произносить-асимптотические-оценки-на-английском)
- [Полезные ресурсы](#полезные-ресурсы)

---

## Описание

$\text{Big O, Big Omega, Big Theta}$ - это математические обозначения для асимптотического поведения функций, также известные как O-нотации. Под асимптотикой понимается характер изменения функции при стремлении её аргумента к бесконечно большому числу. O-нотация активно применяется в математическом анализе и теории алгоритмов.

Формальное определение всех O-нотаций:

![img_5.png](../assets/img_5.png)

### O-нотация и алгоритмы

При оценке алгоритмов нам понадобятся O-нотации, чтобы определять эффективность алгоритма в плане скорости работы или требуемой дополнительной памяти.
Важно понять, что эти обозначения не сообщают *точной* времени работы алгоритма - они лишь показывают *порядок роста* (order of growth) времени или памяти в зависимости от входных данных.

В зависимости от нотации, мы можем сказать нижнюю, верхнюю или обе границы порядка роста функции. Так, Big-O сообщает верхнюю, Big-Omega - нижнюю, а Big-Theta ограничивает функцию как сверху, так и снизу.

Таким образом, O-нотация разделяет алгоритмы на классы эффективности, например: $O(\log n)$, $O(n)$, $O(n * \log n)$, $O(n^2)$ и так далее.

Здесь $n$ - это размер входных данных. Например, мы говорим, что линейный поиск (linear search) относится к классу $O(n)$, потому что его время работы возрастает линейно в зависимости от входных данных. Если мы увеличим размер входных данных, например, с $n$ до $2n$, то и время работы вырастет в 2 раза. А вот если алгоритм работает за время $O(n^2)$, то при увеличении $n$ до $2n$, время работы вырастет в 4 раза.

Как же нам оценить время работы алгоритма в терминах O-нотации? Для этого нам нужно составить функцию, которая считает
точное количество операций алгоритма.

Например, в Википедии хорошо разобран алгоритм "Сортировка вставками" - приведу скриншот из статьи.

<details>
    <summary>Оценка количества операций для сортировки вставками</summary>

![img.png](../assets/insertion_sort_efficiency_function.png)
</details>

Не пугайтесь: под спойлером разобрано очень подробно, но на самом деле все проще. Достаточно изучив и поняв эту тему, вы сможете интуитивно вычислять время работы, понимая что делает каждый шаг алгоритма.

---

### Отбрасывание non-dominant слагаемых

Как я уже сказал, используя асимптотические обозначения, мы хотим показать *порядок роста* функции. Что это значит на
деле? Это значит, что мы отбрасываем:
1. Недоминирующие (non-dominant) слагаемые из функции: меньшие степени и константы
2. Константные множители

Например, если мы оценили, что количество операций алгоритма можно описать такой функцией: $f(n)=5n^2 + n + 10$, то в
итоге мы равно говорим, что эта функция принадлежит к классу $O(n^2)$. Почему же так?

Дело в том, что на достаточно больших входных данных все эти множители и не доминирующие слагаемые *перестают играть
значимую роль*, становятся пренебрежимо малыми по сравнению с бОльшими слагаемыми и множителями. А как мы помним, асимптотика дает оценку роста функции как раз при стремлении ее аргумента к *бесконечному числу*.

Например, давайте сравним рост функций $f(x)=x+\log(x)+16$ и $g(x)=x$, нарисовав их графики. Это на маленьких $x$:

<img src="../assets/desmos-graph-growth-low-n.png" alt="growth-low-n" width="400" height="400"/>

Да, первая функция пока что обходит вторую. А теперь давайте возьмем масштаб поменьше и посмотрим на их рост еще раз:

<img src="../assets/desmos-graph-growth-big-n.png" alt="growth-big-n" width="400" height="400"/>

Мы видим, что их графики уже сравнялись и совершенно не отличимы. Да, конечно, первая функция будет все еще больше, но
вопрос в том, *насколько значительно*. Здесь мы понимаем, что на достаточно больших n основную роль играет только
доминирующее слагаемое.

Таким образом, мы говорим, что функция $f(n)=5n^2 + n + 10$ **асимптотически эквивалентна** функции $n^2$ при $n \to \infty$, что зачастую также записывают как $f(n) \sim n^2$

---

### Логарифм в асимптотике

Когда мы даем асимптотическую оценку, мы не указываем основание логарифма.

Это потому, что нам важно только поведение функции: константное, логарифмическое или линейное. Не важно, какое основание логарифма, важно то, что асимптотически *логарифмическое время всегда лучше линейного*.

---

## Omega-O

Мы используем $\text{Big-Omega}$ ($\text{Big-}\Omega$) когда хотим сказать асимптотически *нижнюю* границу роста функции, то есть алгоритм займет *как минимум* столько-то времени.

### Формальное определение

$f(n)=\Omega(g(n))$ тогда и только тогда, когда существует некоторая положительная константа $c$ и некоторое неотрицательное
число $n_0$, такие что $f(n) >= c*g(n)$ для всех $n >= n_0$.

То есть, если сложность алгоритма $\Omega(g(n))$, то для достаточно больших n время выполнения займет как минимум $c * g(n)$

![img_6.png](../assets/img_6.png)

---

## Theta-O. Asymptotically Tight Bound

Мы используем $\text{Big-Theta}$ ($\text{Big-}\Theta$) когда хотим сказать асимптотически *точную* сложность алгоритма (asymptotically tight bound), то есть алгоритм займет ровно столько-то времени.

Так, если сложность алгоритма $\Theta(g(n))$, то для достаточно больших n время выполнения займет как минимум $c_1 * g(n)$ и как максимум $c_2 * g(n)$ времени для некоторых констант $c_1$ и $c_2$.

![img_7.png](../assets/img_7.png)

### Формальное определение

Верно, что если $f(n) = \Theta(g(n))$, то $f(n) = \Omega(g(n))$ и $f(n) = O(g(n))$

И верно обратное: если $f(n) = \Omega(g(n))$ и $f(n) = O(g(n))$, то $f(n) = \Theta(g(n))$

---

## Big-O

Мы используем $\text{Big-O}$ когда хотим сказать асимптотически *верхнюю* границу роста функции, то есть алгоритм займет как максимум столько-то времени.

Зачастую мы используем как раз Big-O, так как нам интересно только максимальное время работы алгоритма - не путать с лучшим и худшим случаем входных данных.

Например, мы знаем что в худшем случае бинарный поиск работает за время $\Theta(\log_2 n)$. Но нельзя сказать что бинарный поиск
работает за $\Theta(\log_2 n)$ во всех случаях, включая лучшие случаи. Ведь мы можем найти искомое число и на первой итерации.
Тогда это было бы $\Theta(1)$. То есть время выполнения алгоритма никогда не хуже, чем $\Theta(\log_2 n)$, но иногда лучше.

То есть, используя Big-O, мы хотим сказать: время выполнения растет как максимум таким образом, но может расти и медленнее.

### Формальное определение

$f(n)=O(g(n))$ тогда и только тогда, когда существует некоторая положительная константа $c$ и некоторое неотрицательное число $n_0$, такие что $f(n) <= c*g(n)$ для всех $n >= n_0$.

Так, если время выполнения $O(g(n))$, то для достаточно больших n время выполнения займет как максимум $c * g(n)$

![img_8.png](../assets/img_8.png)

---

## Sharp bound

Верно, что время работы бинарного поиска можно описать как $O(n^2)$ или как $O(n)$. Но дело в том, что это будут не максимально точные асимптотические оценки.

Обычно, когда мы используем Big-O или Big-Omega нотации, мы стараемся давать максимально точные асимптотические оценки (sharp bound, tightest bound). То есть такую оценку, которая точно ограничивает время работы функции $f$ снизу или сверху в пределах некоторой константы, но по росту максимально близка к рассматриваемой функции и эту оценку нельзя улучшить. Например, для того же бинарного поиска это $O(\log n)$

---

## Little-O, Little-Omega

Little-O ($o$) и Little-Omega ($\omega$) отличаются от Big нотаций тем, что в их формальном определении знак сравнения строгий и необходимо выполнение условия при любой константе $c$, а не некоторой. Такие нотации мы используем, когда не можем дать асимптотически точную границу (т.е. говорим про loose bound).

Таким образом, в случае Little-O сравниваемая функция $f(n)$ **всегда** растет *строго медленнее*, а в случае Little-Omega *строго быстрее* чем $g(n)$. Например, время выполнения бинарного поиска есть $o(n)$ и $\omega(1)$. Однако, нельзя сказать что бинарный поиск работает за $\omega(\log n)$ или за $o(\log n)$

Очевидно, что Little-Theta не существует.

---

## Пределы

Можно рассмотреть O-нотации с точки зрения пределов.

Если взять две функции $f(n)$ и $g(n)$ при $n \to \infty$, то предел отношения $\frac{f(n)}{g(n)}$ равен:

- $0$, если $f(n)$ имеет меньший порядок роста, чем $g(n)$
- $c$, если $f(n)$ имеет тот же порядок роста, что и $g(n)$
- $\infty$, если $f(n)$ имеет больший порядок роста, чем $g(n)$

Тогда для Big-O нотаций верно:

- Для двух первых случаев: $f(n) = O(g(n))$
- Для двух последних случаев: $f(n) = \Omega(g(n))$
- Для второго случая: $f(n) = \Theta(g(n))$

А для Little-O нотаций верно:

- Для первого случая: $f(n) - o(g(n))$
- для третьего случая: $f(n) - \omega(g(n))$

Это я взял из книги "Алгоритмы. Введение в разработку и анализ. А. Левитин"  - вот выдержка из нее:

![](../assets/Порядок_роста_функций_(пределы).png)

---

## Как произносить асимптотические оценки на английском

- Сами нотация так и произносятся - Big-O(h), Big-Omega, Big-Theta
- Как давать оценки:
    - $O(1)$ - big oh of one; constant time
    - $O(\log n)$ - big oh of log n; logarithmic
    - $O(n)$ - big oh of N; linear time
    - $O(n^2)$ - big oh of n squared; quadratic
    - $O(2^n)$ - big oh of power of two; exponential
- Случаи: worst case, best case, average case

---

## Полезные ресурсы

- Полезный сайт с оценками всех популярных алгоритмов - <https://www.bigocheatsheet.com/>
- В этой директории есть файл `o_O.pdf` - это конспект уроков линейной алгебры и математического анализа из ВШЭ
- Книга "Алгоритмы. Введение в разработку и анализ" - А. Левитин
- [Wiki: Асимптотический анализ](https://ru.wikipedia.org/wiki/%D0%90%D1%81%D0%B8%D0%BC%D0%BF%D1%82%D0%BE%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%D0%B0%D0%BD%D0%B0%D0%BB%D0%B8%D0%B7)
- [Wiki: «O» большое и «o» малое](https://ru.wikipedia.org/wiki/%C2%ABO%C2%BB_%D0%B1%D0%BE%D0%BB%D1%8C%D1%88%D0%BE%D0%B5_%D0%B8_%C2%ABo%C2%BB_%D0%BC%D0%B0%D0%BB%D0%BE%D0%B5)
- [Wiki: Асимптота](https://ru.wikipedia.org/wiki/%D0%90%D1%81%D0%B8%D0%BC%D0%BF%D1%82%D0%BE%D1%82%D0%B0)
