# Java 程序性能优化

这也是一本书，里面有很多干货，看的我不要不要的。就在这里敲一遍要点吧，权当增强记忆了。

全文都以简短的tips作为小结，尽量做到最精简。

## 设计优化

## Java 程序优化

1. str.substring(begin, end)存在内存泄漏风险（当str长度很大时）（该方法牺牲了空间换取时间） ==> new String(str.substring(begin, end))
2. 分割方法split()功能强大，但效率差，不如使用 StringTokenizer （自己的Util里自定义一个分割的方法，使用StringTokenizer）
3. 如事先知道容量，则初始化的时候就设定好。eg:StringBuilder sb = new StringBuilder(16); 因为容量不足时，往往是翻倍或者1.5倍扩容的
4. List遍历：作者构造一个拥有100万数据的ArrayList和等价的LinkedList，分别用下面的3种方法获得其中数据，对应的耗时

| List类型 | ForEach操作 | 迭代器 | for循环 |
| ----- | ----- | ----- | ----- |
| ArrayList | 63ms | 47ms | 31ms |
| LinkedList | 63ms | 47ms | 很大 |

ArrayList 实现了RandomAccess接口，支持随机访问，故使用for循环最快，而LinkedList没有实现该接口，使用迭代器最快。

## 并行程序开发及优化

## JVM调优

貌似Android是跑DVM的，跟JVM也算沾点边吧，这个最后再看

## Java 性能调优工具

