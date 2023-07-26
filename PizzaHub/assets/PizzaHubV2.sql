CREATE TABLE [Role] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] String UNIQUE NOT NULL
)
GO

CREATE TABLE [MenuItemCategory] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] String UNIQUE NOT NULL
)
GO

CREATE TABLE [Product] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] String NOT NULL,
  [stock] Int DEFAULT (0)
)
GO

CREATE TABLE [MenuItem] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] String NOT NULL,
  [price] Double NOT NULL,
  [category_id] Int NOT NULL,
  [products] Int[] NOT NULL
)
GO

CREATE TABLE [Pizza] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [menu_item_id] Int UNIQUE NOT NULL,
  [pizza_base_id] Int NOT NULL
)
GO

CREATE TABLE [Order] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [is_completed] Bool NOT NULL DEFAULT (false)
)
GO

CREATE TABLE [RestaurantOrder] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [order_id] Int UNIQUE NOT NULL,
  [table_number] Int NOT NULL,
  [seats] Int NOT NULL,
  [order_step] Int NOT NULL DEFAULT (1),
  [menu_items] Int[] NOT NULL
)
GO

CREATE TABLE [TimeSlot] (
  [time_slot_id] Int PRIMARY KEY IDENTITY(1, 1),
  [reserved] Bool NOT NULL DEFAULT (false),
  [slot] Datetime UNIQUE NOT NULL
)
GO

CREATE TABLE [OnlineOrder] (
  [online_order_id] Int PRIMARY KEY IDENTITY(1, 1),
  [order_id] Int UNIQUE NOT NULL,
  [customer_name] String NOT NULL,
  [time_slot_id] Int NOT NULL,
  [pizzas] Int[] NOT NULL
)
GO

CREATE TABLE [Employee] (
  [employee_id] Int PRIMARY KEY IDENTITY(1, 1),
  [first_name] String NOT NULL,
  [last_name] String NOT NULL,
  [password] Hash UNIQUE NOT NULL,
  [email] String NOT NULL,
  [puuid] String UNIQUE NOT NULL,
  [role_name] Int NOT NULL
)
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
@value = 'APPETIZER, MAIN_COURSE, DESSERT',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'MenuItemCategory',
@level2type = N'Column', @level2name = 'name';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = '10:00, 10:15, 10:30...',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'TimeSlot',
@level2type = N'Column', @level2name = 'slot';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'can you autogenerate this ?',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Employee',
@level2type = N'Column', @level2name = 'password';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'and autogenerate this ?',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Employee',
@level2type = N'Column', @level2name = 'puuid';
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

ALTER TABLE [Pizza] ADD FOREIGN KEY ([pizza_base_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [RestaurantOrder] ADD FOREIGN KEY ([order_id]) REFERENCES [Order] ([id])
GO

CREATE TABLE [MenuItem_RestaurantOrder] (
  [MenuItem_id] Int,
  [RestaurantOrder_menu_items] Int[],
  PRIMARY KEY ([MenuItem_id], [RestaurantOrder_menu_items])
);
GO

ALTER TABLE [MenuItem_RestaurantOrder] ADD FOREIGN KEY ([MenuItem_id]) REFERENCES [MenuItem] ([id]);
GO

ALTER TABLE [MenuItem_RestaurantOrder] ADD FOREIGN KEY ([RestaurantOrder_menu_items]) REFERENCES [RestaurantOrder] ([menu_items]);
GO


ALTER TABLE [OnlineOrder] ADD FOREIGN KEY ([order_id]) REFERENCES [Order] ([id])
GO

ALTER TABLE [OnlineOrder] ADD FOREIGN KEY ([time_slot_id]) REFERENCES [TimeSlot] ([time_slot_id])
GO

CREATE TABLE [Pizza_OnlineOrder] (
  [Pizza_id] Int,
  [OnlineOrder_pizzas] Int[],
  PRIMARY KEY ([Pizza_id], [OnlineOrder_pizzas])
);
GO

ALTER TABLE [Pizza_OnlineOrder] ADD FOREIGN KEY ([Pizza_id]) REFERENCES [Pizza] ([id]);
GO

ALTER TABLE [Pizza_OnlineOrder] ADD FOREIGN KEY ([OnlineOrder_pizzas]) REFERENCES [OnlineOrder] ([pizzas]);
GO


ALTER TABLE [Employee] ADD FOREIGN KEY ([role_name]) REFERENCES [Role] ([name])
GO
