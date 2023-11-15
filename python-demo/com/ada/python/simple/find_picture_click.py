"""
功能：
    查找template_path的图片在image_path这种图片下的xy坐标，并移动鼠标点击 
参数示例：
     image_path = "E:\\NC\\picture\\img.jpg"
     template_path = "E:\\NC\\picture\\template.jpg"
安装依赖：
      E:\UERPA\UERPA\python-3.7.3-embed-amd64\python.exe  -m pip install pyautogui
"""
import cv2
import pyautogui
from pynput import mouse
from PIL import ImageGrab


# 查找image_path图中，template_path目标图像的坐标 
def match_template(image_path, template_path):
    # 读取图像
    image = cv2.imread(image_path)
    template = cv2.imread(template_path)
    # 返回匹配的结果
    result = cv2.matchTemplate(image, template, cv2.TM_CCOEFF_NORMED)
    min_val, max_val, min_loc, max_loc = cv2.minMaxLoc(result)
    top_left = max_loc
    bottom_right = (top_left[0] + template.shape[1], top_left[1] + template.shape[0])

    # 4.画矩形
    cv2.rectangle(image, top_left, bottom_right, (255, 0, 0), 5)
    cv2.imwrite(image_path, image)
    m = mouse.Controller()
    m.position = top_left
    # 返回左上角、右下角的坐标
    return top_left, bottom_right


# 计算出鼠标要点击的x/y坐标
def get_click_point(top_left, bottom_right):
    # 鼠标左键点击的坐标
    x = (top_left[0] + bottom_right[0]) / 2
    y = (top_left[1] + bottom_right[1]) / 2
    return x, y


# 根据xy坐标点击
def on_click(x, y):
    # 单击鼠标左键
    # 参数1：移动x坐标到指定位置
    # 参数2：移动y坐标到指定位置
    # 参数3：点击次数
    # 无参数：在当前坐标单击
    pyautogui.click(x, y)


# 代码执行顺序
image_path = "E:\\NC\\picture\\img.jpg"

# （目标图片）的路径
template_path = "E:\\NC\\picture\\template.jpg"

# 截图
pic = ImageGrab.grab()

# 保存图片
pic.save(image_path)

# 获取目标图片左上角、右下角的坐标
top_left, bottom_right = match_template(image_path, template_path)
print("左上角、右下角的坐标分别是：", (top_left, bottom_right))

# 获取鼠标即将点击的x,y坐标
x, y = get_click_point(top_left, bottom_right)
print("鼠标点击的[x,y]坐标是：", (x, y))

# 点击x,y坐标
on_click(x, y)


