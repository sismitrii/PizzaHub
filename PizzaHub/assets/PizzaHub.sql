CREATE TABLE [TableNumber] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [number] Int UNIQUE
)
GO

CREATE TABLE [Role] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [name] String UNIQUE
)
GO

CREATE TABLE [MenuItemCategory] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [name] String UNIQUE
)
GO

CREATE TABLE [Product] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [name] String,
  [stock] Int
)
GO

CREATE TABLE [PizzaBase] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [product_id] Int
)
GO

CREATE TABLE [MenuItem] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [name] String,
  [price] Double,
  [category_id] Int,
  [products] Int[]
)
GO

CREATE TABLE [Pizza] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [menu_item_id] Int,
  [pizza_base_id] Int
)
GO

CREATE TABLE [Order] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [is_completed] Bool
)
GO

CREATE TABLE [RestaurantOrder] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [order_id] Int,
  [table_number] Int,
  [seats] Int,
  [order_step] Int,
  [menu_items] Int[]
)
GO

CREATE TABLE [OnlineOrder] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [order_id] Int,
  [customer_name] String,
  [time_slot] datetime,
  [pizzas] Int[]
)
GO

CREATE TABLE [Employee] (
  [id] Int PRIMARY KEY,
  [created_at] Timestamp,
  [updated_at] Timestamp,
  [first_name] String,
  [last_name] String,
  [password] Hash,
  [email] String,
  [puuid] String,
  [role_name] Int
)
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'TableNumber',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'TableNumber',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'TableNumber',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Role',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Role',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Role',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'WAITER, COOK, MANAGER',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Role',
@level2type = N'Column', @level2name = 'name';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItemCategory',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItemCategory',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItemCategory',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'APPETIZER, MAIN_COURSE, DESSERT',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItemCategory',
@level2type = N'Column', @level2name = 'name';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Product',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Product',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Product',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'PizzaBase',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'PizzaBase',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'PizzaBase',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItem',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItem',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItem',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Pizza',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Pizza',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Pizza',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Order',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Order',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Order',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'RestaurantOrder',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'RestaurantOrder',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'RestaurantOrder',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'OnlineOrder',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'OnlineOrder',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'OnlineOrder',
@level2type = N'Column', @level2name = 'updated_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Employee',
@level2type = N'Column', @level2name = 'id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Employee',
@level2type = N'Column', @level2name = 'created_at';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'auto-generated',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Employee',
@level2type = N'Column', @level2name = 'updated_at';
GO

ALTER TABLE [PizzaBase] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [MenuItem] ADD FOREIGN KEY ([category_id]) REFERENCES [MenuItemCategory] ([id])
GO

CREATE TABLE [Product_MenuItem] (
  [Product_id] Int,
  [MenuItem_products] Int[],
  PRIMARY KEY ([Product_id], [MenuItem_products])
);
GO

ALTER TABLE [Product_MenuItem] ADD FOREIGN KEY ([Product_id]) REFERENCES [Product] ([id]);
GO

ALTER TABLE [Product_MenuItem] ADD FOREIGN KEY ([MenuItem_products]) REFERENCES [MenuItem] ([products]);
GO


ALTER TABLE [Pizza] ADD FOREIGN KEY ([menu_item_id]) REFERENCES [MenuItem] ([id])
GO

ALTER TABLE [Pizza] ADD FOREIGN KEY ([pizza_base_id]) REFERENCES [PizzaBase] ([id])
GO

ALTER TABLE [RestaurantOrder] ADD FOREIGN KEY ([order_id]) REFERENCES [Order] ([id])
GO

ALTER TABLE [RestaurantOrder] ADD FOREIGN KEY ([table_number]) REFERENCES [TableNumber] ([number])
GO

ALTER TABLE [RestaurantOrder] ADD FOREIGN KEY ([menu_items]) REFERENCES [MenuItem] ([id])
GO

ALTER TABLE [OnlineOrder] ADD FOREIGN KEY ([order_id]) REFERENCES [Order] ([id])
GO

ALTER TABLE [OnlineOrder] ADD FOREIGN KEY ([pizzas]) REFERENCES [Pizza] ([id])
GO

ALTER TABLE [Employee] ADD FOREIGN KEY ([role_name]) REFERENCES [Role] ([name])
GO
