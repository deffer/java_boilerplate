--local f = require 'org.tests.luaj.libs.randomstreams'
--print (f)

local testStreams = function(val)
    print(rstreams)
    print(rstreams.num)
    print(rstreams.num(val))
end

testStreams(5);
print (random_pc_stream(4))