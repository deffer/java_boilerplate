
moveToCenter = function()
    print "Checking cell"

    local content = world.getAt(15,15)
    print("Content of cells is "..tostring(content).." of class "..type(content))

    for i,k in ipairs(content) do
        print("Content is "..tostring(k))
    end

    print "Moving to 15,15"
    world.moveTo(15,15);

    local content = world.getAt(15,15)
    for i,k in ipairs(content) do
        print("Content is "..tostring(k))
    end
end

world.hookOn("world_created", moveToCenter)


