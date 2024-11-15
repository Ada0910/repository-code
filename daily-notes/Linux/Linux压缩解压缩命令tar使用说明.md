

在 Linux 中，tar命令是常用的用于打包和压缩文件的工具。以下是关于它的压缩和解压命令的介绍

# **压缩文件**

```text
打包文件（不进行压缩）

1.1命令：tar -cvf 目标文件名.tar 源文件或目录

例如：tar -cvf myfiles.tar file1.txt file2.txt directory1，这会将file1.txt、file2.txt和directory1打包成一个名为myfiles.tar的文件
参数解释：
-c：创建新的归档文件。
-v：显示详细信息。
-f：指定归档文件名
```

```text
1.2打包并压缩文件（使用 gzip 压缩）：

命令：tar -czvf 目标文件名.tar.gz 源文件或目录

例如：tar -czvf mycompressedfiles.tar.gz file1.txt file2.txt directory1，这会将指定的文件和目录打包并使用 gzip 压缩成一个.tar.gz文件。
参数解释：
-c：创建新的归档文件。
-z：使用 gzip 压缩。
-v：显示详细信息。
-f：指定归档文件名。
```

```text
1.3打包并压缩文件（使用 bzip2 压缩）：

命令：tar -cjvf 目标文件名.tar.bz2 源文件或目录

例如：tar -cjvf mycompressedfiles.tar.bz2 file1.txt file2.txt directory1，这会将指定的文件和目录打包并使用 bzip2 压缩成一个.tar.bz2文件。
参数解释：
-c：创建新的归档文件。
-j：使用 bzip2 压缩。
-v：显示详细信息。
-f：指定归档文件名。
```

# 解压文件

```text
2.1解压 tar 文件（不进行压缩的情况）：

命令：tar -xvf 文件名.tar

例如：tar -xvf myfiles.tar，这会将myfiles.tar中的文件解压到当前目录。
参数解释：
-x：解包归档文件。
-v：显示详细信息。
-f：指定归档文件名。
```

```text
2.2解压 tar.gz 文件：

命令：tar -xzvf 文件名.tar.gz

例如：tar -xzvf mycompressedfiles.tar.gz，这会将压缩的.tar.gz文件解压到当前目录。
参数解释：
-x：解包归档文件。
-z：使用 gzip 解压缩。
-v：显示详细信息。
-f：指定归档文件名。

```

```text
2.3解压 tar.bz2 文件：

命令：tar -xjvf 文件名.tar.bz2

例如：tar -xjvf mycompressedfiles.tar.bz2，这会将压缩的.tar.bz2文件解压到当前目录。
参数解释：
-x：解包归档文件。
-j：使用 bzip2 解压缩。
-v：显示详细信息。
-f：指定归档文件名。

```

# 压缩命令

## 安装

```text
yum -y install zip unzip
```

## zip命令

```text
zip -r /home/test.zip /home/test

将/home/test目录下的文件压缩成zip包，并将zip包存放在/home目录下，且zip包中保留目录层级结构
```

## Unzip

```text
unzip -o test.zip

解压然后替换当前目录的包 
```

