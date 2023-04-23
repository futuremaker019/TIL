def recursive(data) : 
    if data < 0 :
        print("endend")
    else :
        print(data)
        recursive(data - 1)
        print("returned", data)

recursive(5)