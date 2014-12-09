checkStores = function(stores, favoriteFruit)
    print("Stores are "..tostring(stores).." of class "..tostring(type(stores)))
    local firstFruit
    local lastFruit
    for i,s in ipairs(stores) do
        local fruit = s:getSellingFruit()
        if i==1 then firstFruit = fruit else lastFruit = fruit end
        print("Store sells "..tostring(fruit).." of class "..type(fruit))
    end
    print("Fruit are "..(firstFruit==lastFruit and "" or "not").." identical")
    if (firstFruit == lastFruit) then
        print("Fuit is "..(firstFruit==favoriteFruit and "" or "not").." favorite")
        if firstFruit==favoriteFruit then print("Woo hoo!") end
    end
end

