# 클래스 정의
class Quadrangle:     
  width = 0
  height = 0
  color = "black"

  # 생성자
  def __init__(self, width, height, color):
    self.width = width
    self.height = height
    self.color = color

  # 소멸자
  def __del__(self):
    print("object is deleted")


# 메서드 정의 : 메서드에는 항상 파라미터로 self(자기 자신)가 들어간다.
  def get_area(self):   
    return self.width * self.height

  def set_area(self, data1, data2):
    self.width = data1
    self.height = data2


shape = Quadrangle(5, 10, "white");

del shape