local a = 5;
local b = 10;
local getLimit = function(a1, a2)
    return math.min(a1+a2, 12)
end

local food = {"fruits", "meat", "cheese", "protein bars"}

print('Welcome to new world, stranger. G is limited to '..tostring(getLimit(a, b)))
print 'Food available to you: '

for i,v in ipairs(food) do
    print("  "..v)
end



local borderLayout = luajava.bindClass("java.awt.BorderLayout")
local jframe = luajava.bindClass("javax.swing.JFrame")

local secretObject = luajava.newInstance("org.tests.luaj.ProtectedClass")
print(tostring(secretObject))
local secretText = secretObject:getSecret()

local frame = luajava.newInstance("javax.swing.JFrame", "Sample Luaj Application");
local content = frame:getContentPane()
local label = luajava.newInstance("javax.swing.JLabel", "I know the secret: "..secretText)
content:add(label, borderLayout.CENTER)
frame:setDefaultCloseOperation(jframe.EXIT_ON_CLOSE)
frame:pack()


frame:setVisible(true)
