checkStores = function(stores)
    print("Stores are "..tostring(stores).." of class "..tostring(type(stores)))
    print("Stores size is "..tostring(stores:size()))
    for s,i in ipairs(stores) do
        print("Store sells "..tostring(s:getFruit()))
    end
end

