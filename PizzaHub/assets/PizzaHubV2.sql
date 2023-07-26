CREATE TABLE [Role] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] VARCHAR(50) UNIQUE NOT NULL
)
GO

CREATE TABLE [MenuItemCategory] (
  [id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] VARCHAR(50) UNIQUE NOT NULL
)
GO

CREATE TABLE [Product] (
  [product_id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] VARCHAR(255) NOT NULL,
  [stock] Int DEFAULT (0)
)
GO

CREATE TABLE [MenuItem] (
  [menu_item_id] Int PRIMARY KEY IDENTITY(1, 1),
  [name] VARCHAR(255) NOT NULL,
  [price] DECIMAL(10,2) NOT NULL,
  [category_id] Int NOT NULL FOREIGN KEY REFERENCES [MenuItemCategory] ([id]),
--  [products] Int[] NOT NULL -- TODOOOOOO
)
GO

CREATE TABLE [Product_MenuItem] (
  [product_id] Int FOREIGN KEY REFERENCES [Product]([product_id]),
  [menu_item_id] Int FOREIGN KEY  REFERENCES [MenuItem]([menu_item_id]),
  PRIMARY KEY ([product_id], [menu_item_id])
);
GO

CREATE TABLE [Pizza] (
  [pizza_id] Int PRIMARY KEY IDENTITY(1, 1),
  [menu_item_id] Int UNIQUE NOT NULL FOREIGN  KEY REFERENCES [MenuItem]([menu_item_id]),
  [picture_url] VARCHAR(255)
)
GO

CREATE TABLE [Order] (
  [order_id] Int PRIMARY KEY IDENTITY(1, 1),
  [is_completed] BIT NOT NULL DEFAULT (0)
)
GO

CREATE TABLE [RestaurantOrder] (
  [restaurantOrder_id] Int PRIMARY KEY IDENTITY(1, 1),
  [order_id] Int UNIQUE NOT NULL FOREIGN KEY REFERENCES [Order]([order_id]) ,
  [table_number] Int NOT NULL,
  [seats] Int NOT NULL,
  [order_step] Int NOT NULL DEFAULT (1),
  --[menu_items] Int[] NOT NULL -- TODOOOOOO
)
GO

CREATE TABLE [MenuItem_RestaurantOrder] (
  [menuItem_id] Int FOREIGN  KEY REFERENCES [MenuItem]([menu_item_id]),
  [restaurantOrder_id] Int FOREIGN KEY REFERENCES [RestaurantOrder]([restaurantOrder_id])
  PRIMARY KEY ([menuItem_id], [restaurantOrder_id])
);
GO

CREATE TABLE [TimeSlot] (
  [time_slot_id] Int PRIMARY KEY IDENTITY(1, 1),
  [reservedPizza] Int NOT NULL DEFAULT (0),
  [slot] Datetime UNIQUE NOT NULL
)
GO

CREATE TABLE [OnlineOrder] (
  [online_order_id] Int PRIMARY KEY IDENTITY(1, 1),
  [order_id] Int UNIQUE NOT NULL FOREIGN KEY REFERENCES [Order]([order_id]),
  [customer_name] VARCHAR(255) NOT NULL,
  [time_slot_id] Int NOT NULL FOREIGN KEY REFERENCES [TimeSlot]([time_slot_id]),
  --[pizzas] Int[] NOT NULL -- TODOOOOOO
)
GO

CREATE TABLE [Pizza_OnlineOrder] (
  [pizza_id] Int FOREIGN KEY REFERENCES [Pizza]([pizza_id]),
  [online_order_id] Int FOREIGN KEY REFERENCES [OnlineOrder]([online_order_id]),
  PRIMARY KEY ([pizza_id], [online_order_id])
);
GO

CREATE TABLE [Employee] (
  [employee_id] Int PRIMARY KEY IDENTITY(1, 1),
  [first_name] VARCHAR(255) NOT NULL,
  [last_name] VARCHAR(255) NOT NULL,
  [password] VARCHAR(255) UNIQUE NOT NULL,
  [email] VARCHAR(255) NOT NULL,
  [puuid] CHAR(4) UNIQUE NOT NULL,
  [role_id] Int NOT NULL FOREIGN KEY REFERENCES [Role]([id])
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
