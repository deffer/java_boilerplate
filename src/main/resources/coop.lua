local a = 5;
local b = 10;
local getLimit = function(a1, a2)
    return math.min(a1+a2, 12)
end

food = {"fruits", "meat", "cheese", "protein bars"}
local printFood = function()
    print 'Food available to you: '

    for i,v in ipairs(food) do
        print("  "..v)
    end
end

print('Welcome to new world, stranger. G is limited to '..tostring(getLimit(a, b)))
printFood()

