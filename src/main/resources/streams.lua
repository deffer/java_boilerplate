--local f = require 'org.tests.luaj.libs.randomstreams'
--print (f)

local testStreams = function(val)
    print(randomstreams)
    print(randomstreams.rs)
    print(randomstreams.rs(val))
end

testStreams(5);