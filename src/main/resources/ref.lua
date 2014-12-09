
local cachedFruit
cacheFavoriteFruit = function(fruit)
    cachedFruit = fruit
end

checkStores = function(stores, favoriteFruit)
    print("Stores are "..tostring(stores).." of class "..tostring(type(stores)))
    local firstFruit
    local lastFruit
    for i,s in ipairs(stores) do
        local fruit = s:getSellingFruit()
        if i==1 then firstFruit = fruit else lastFruit = fruit end
        print("Store "..tostring(s.id).." sells "..tostring(fruit).." of class "..type(fruit))
    end

    print("Fruits are"..(firstFruit==lastFruit and "" or " NOT").." identical")
    print("Fuit is"..(firstFruit==favoriteFruit and "" or " NOT").." favorite")
    print("Fuit is"..(firstFruit==cachedFruit and "" or " NOT").." equal to cached")

    if (injectedFruit ~= nil) then
        print("Fuit is"..(firstFruit==injectedFruit and "" or " NOT").." equal to injected")
    end

    if (injectedFruitNewCoerce ~= nil) then
        print("Fuit is"..(firstFruit==injectedFruitNewCoerce and "" or " NOT").." equal to injected-new-coerce")
        if firstFruit==injectedFruitNewCoerce then print("Woo hoo!") end
    end
end

