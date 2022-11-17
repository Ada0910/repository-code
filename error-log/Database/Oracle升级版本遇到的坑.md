# Oracle升级过程中遇到的坑
用命令查看版本号：select version from v$instance;
原来的版本：11.2.0.4.0
升级后的版本：19.0.0.0.0

# 遇到的坑
## max()函数会自动忽略第一个属性，也就是说，max(a.b.属性）a可以是任意字符，b是某个字符结合
## wm_contact（）函数会失效