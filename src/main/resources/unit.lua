
moveToCenter = function()
    print "Moving to 15,15"
    world.moveTo(15,15);
end

world.hookOn("world_created", moveToCenter)


