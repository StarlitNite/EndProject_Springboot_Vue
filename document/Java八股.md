# Java八股笔记

#### 一、HashCode与equals()如何使用

![image-20230312042632085](E:\IdeaProjects\EndProject\document\八股截图\HashCode.png)

HashCode相同：数组一个格只占一个数据，HashCode值相同时就在数据后追加一个数据(链表形式)/或者使用线性探测的方式解决

如果两个完全相同的对象，也就是内存地址指向同一个，那么他们的HashCode值一定相同

前7后8: jdk1.7 追加在前，1.8追加在后

#### 二、String、StringBuffer、StringBuilder的区别

Java提供了String和StrBuffer/StrBuilder两种方式去操作储存字符串

区别在于String引用的字符串内容是不可变的，造成内存浪费，而StringBuffer和StringBuilder表示的字符串可以进行修改，其使用数组进行字符串的存储，包涵Value和Count两个属性，若旧的数组存满则进行扩容，Value指向新的数组，并将旧的数组拷贝至新的数组并继续往后书写。

StringBuffer和StringBuilder的区别在于StringBuilder是在单线程环境下使用，因为他的所有方法没有被synchronized修饰，线程不安全，StringBuffer线程安全，但效率不如StringBuilder。