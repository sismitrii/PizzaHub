USE [master]
GO
/****** Object:  Database [PizzaHubBDD2]    Script Date: 04/08/2023 10:35:08 ******/
CREATE DATABASE [PizzaHubBDD2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PizzaHubBDD2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PizzaHubBDD2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PizzaHubBDD2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PizzaHubBDD2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [PizzaHubBDD2] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PizzaHubBDD2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PizzaHubBDD2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET ARITHABORT OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PizzaHubBDD2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PizzaHubBDD2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PizzaHubBDD2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PizzaHubBDD2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET RECOVERY FULL 
GO
ALTER DATABASE [PizzaHubBDD2] SET  MULTI_USER 
GO
ALTER DATABASE [PizzaHubBDD2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PizzaHubBDD2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PizzaHubBDD2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PizzaHubBDD2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PizzaHubBDD2] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PizzaHubBDD2', N'ON'
GO
ALTER DATABASE [PizzaHubBDD2] SET QUERY_STORE = OFF
GO
USE [PizzaHubBDD2]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[employee_id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [varchar](255) NOT NULL,
	[last_name] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[puuid] [char](4) NOT NULL,
	[role_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuItem]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuItem](
	[menu_item_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NOT NULL,
	[price] [decimal](10, 2) NOT NULL,
	[category_id] [int] NOT NULL,
	[size] [varchar](3) NULL,
PRIMARY KEY CLUSTERED 
(
	[menu_item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuItem_Order]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuItem_Order](
	[menu_item_id] [int] NULL,
	[order_id] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuItemCategory]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuItemCategory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OnlineOrder]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OnlineOrder](
	[online_order_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[customer_name] [varchar](255) NOT NULL,
	[time_slot_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[online_order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[is_to_Prepare] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pizza]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pizza](
	[pizza_id] [int] IDENTITY(1,1) NOT NULL,
	[menu_item_id] [int] NOT NULL,
	[picture_url] [varchar](255) NULL,
	[size] [varchar](3) NULL,
PRIMARY KEY CLUSTERED 
(
	[pizza_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NOT NULL,
	[stock] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product_MenuItem]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_MenuItem](
	[product_id] [int] NOT NULL,
	[menu_item_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[menu_item_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RestaurantOrder]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RestaurantOrder](
	[restaurantOrder_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[table_number] [int] NOT NULL,
	[seats] [int] NOT NULL,
	[order_step] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[restaurantOrder_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeSlot]    Script Date: 04/08/2023 10:35:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeSlot](
	[time_slot_id] [int] IDENTITY(1,1) NOT NULL,
	[reservedPizza] [int] NOT NULL,
	[slot] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[time_slot_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([employee_id], [first_name], [last_name], [password], [email], [puuid], [role_id]) VALUES (3, N'prenom', N'nom', N'password', N'prenom.nom@gmail.com', N'wait', 1)
SET IDENTITY_INSERT [dbo].[Employee] OFF
SET IDENTITY_INSERT [dbo].[MenuItem] ON 

INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (1, N'Calzone', CAST(9.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (2, N'Pasta al pesto', CAST(14.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (3, N'chevre chaud', CAST(8.90 AS Decimal(10, 2)), 1, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (5, N'Panna Cotta', CAST(7.90 AS Decimal(10, 2)), 4, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (6, N'Calzone', CAST(15.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (7, N'Margarita', CAST(8.50 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (8, N'Hawaienne', CAST(13.20 AS Decimal(10, 2)), 3, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (9, N'Indienne', CAST(12.20 AS Decimal(10, 2)), 3, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (10, N'4 Fromages', CAST(9.90 AS Decimal(10, 2)), 3, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (11, N'Reine', CAST(9.90 AS Decimal(10, 2)), 3, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (12, N'Escargots de Bourgogne', CAST(7.50 AS Decimal(10, 2)), 1, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (13, N'Crème brulée', CAST(3.90 AS Decimal(10, 2)), 4, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (14, N'Steak Frites', CAST(9.20 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (15, N'Fish and chips', CAST(8.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (16, N'Végétarienne', CAST(14.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (17, N'Végétarienne', CAST(17.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (18, N'4 Fromages', CAST(14.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (19, N'4 Fromages', CAST(17.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (20, N'Chevre Miel', CAST(12.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (21, N'Chevre Miel', CAST(15.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (22, N'Salmone', CAST(15.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (23, N'Salmone', CAST(18.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (24, N'Vesuvio', CAST(14.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (25, N'Vesuvio', CAST(17.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (26, N'Catania', CAST(13.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (27, N'Catania', CAST(16.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (28, N'Montagnarde', CAST(15.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (29, N'Catania', CAST(18.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (30, N'Montagnarde', CAST(15.90 AS Decimal(10, 2)), 3, N'M')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (31, N'Montagnarde', CAST(18.90 AS Decimal(10, 2)), 3, N'L')
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (32, N'PIATTO DI SALUMI', CAST(8.90 AS Decimal(10, 2)), 1, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (33, N'Burrata et carpaccio de tomates cerise', CAST(12.90 AS Decimal(10, 2)), 1, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (34, N'Pinsa Bufala', CAST(12.90 AS Decimal(10, 2)), 1, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (35, N'Pinsa Speck', CAST(12.90 AS Decimal(10, 2)), 1, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (36, N'RISOTTO FUNGHI E POLLO', CAST(15.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (37, N'LASAGNE ALLA BOLOGNESE', CAST(13.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (38, N'GIRASOLI AL SALMONE', CAST(15.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (39, N'GNOCCHI AL POLLO', CAST(15.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (40, N'LINGUINE POLLO E PESTO', CAST(16.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (41, N'FILET DE POULET À LA MILANAISE', CAST(16.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (42, N'VEAU MARENGO', CAST(15.90 AS Decimal(10, 2)), 2, NULL)
INSERT [dbo].[MenuItem] ([menu_item_id], [name], [price], [category_id], [size]) VALUES (43, N'Margarita', CAST(11.50 AS Decimal(10, 2)), 3, N'L')
SET IDENTITY_INSERT [dbo].[MenuItem] OFF
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (1, 1)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (2, 1)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 1)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 1)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (2, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (2, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (2, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (2, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (5, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (5, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (5, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (5, 14)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (6, 10)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (1, 17)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (1, 17)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 4)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (3, 4)
INSERT [dbo].[MenuItem_Order] ([menu_item_id], [order_id]) VALUES (1, 9)
SET IDENTITY_INSERT [dbo].[MenuItemCategory] ON 

INSERT [dbo].[MenuItemCategory] ([id], [name]) VALUES (4, N'DESSERT')
INSERT [dbo].[MenuItemCategory] ([id], [name]) VALUES (1, N'ENTREE')
INSERT [dbo].[MenuItemCategory] ([id], [name]) VALUES (3, N'PIZZA')
INSERT [dbo].[MenuItemCategory] ([id], [name]) VALUES (2, N'PLAT')
SET IDENTITY_INSERT [dbo].[MenuItemCategory] OFF
SET IDENTITY_INSERT [dbo].[OnlineOrder] ON 

INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (1, 9, N'BOB', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (2, 10, N'BILL', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (3, 11, N'BOULE', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (4, 12, N'BRICE', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (5, 19, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (6, 20, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (7, 21, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (8, 22, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (9, 23, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (10, 24, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (11, 25, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (12, 26, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (13, 27, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (14, 28, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (15, 29, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (16, 30, N'Shrek', 1)
INSERT [dbo].[OnlineOrder] ([online_order_id], [order_id], [customer_name], [time_slot_id]) VALUES (17, 40, N'Shrek', 9)
SET IDENTITY_INSERT [dbo].[OnlineOrder] OFF
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (1, 1)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (2, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (4, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (9, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (10, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (11, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (12, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (14, 1)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (17, 1)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (19, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (20, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (21, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (22, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (23, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (24, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (25, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (26, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (27, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (28, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (29, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (30, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (31, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (32, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (33, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (34, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (35, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (36, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (37, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (38, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (39, 0)
INSERT [dbo].[Order] ([order_id], [is_to_Prepare]) VALUES (40, 0)
SET IDENTITY_INSERT [dbo].[Order] OFF
SET IDENTITY_INSERT [dbo].[Pizza] ON 

INSERT [dbo].[Pizza] ([pizza_id], [menu_item_id], [picture_url], [size]) VALUES (1, 1, NULL, N'L')
INSERT [dbo].[Pizza] ([pizza_id], [menu_item_id], [picture_url], [size]) VALUES (4, 1, NULL, N'M')
SET IDENTITY_INSERT [dbo].[Pizza] OFF
SET IDENTITY_INSERT [dbo].[RestaurantOrder] ON 

INSERT [dbo].[RestaurantOrder] ([restaurantOrder_id], [order_id], [table_number], [seats], [order_step]) VALUES (2, 1, 12, 4, 1)
INSERT [dbo].[RestaurantOrder] ([restaurantOrder_id], [order_id], [table_number], [seats], [order_step]) VALUES (3, 2, 9, 4, 1)
INSERT [dbo].[RestaurantOrder] ([restaurantOrder_id], [order_id], [table_number], [seats], [order_step]) VALUES (5, 4, 2, 2, 1)
INSERT [dbo].[RestaurantOrder] ([restaurantOrder_id], [order_id], [table_number], [seats], [order_step]) VALUES (11, 14, 4, 4, 1)
INSERT [dbo].[RestaurantOrder] ([restaurantOrder_id], [order_id], [table_number], [seats], [order_step]) VALUES (14, 17, 1, 2, 1)
SET IDENTITY_INSERT [dbo].[RestaurantOrder] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [name]) VALUES (2, N'CUISINIER')
INSERT [dbo].[Role] ([id], [name]) VALUES (3, N'MANAGER')
INSERT [dbo].[Role] ([id], [name]) VALUES (1, N'SERVEUR')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[TimeSlot] ON 

INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (1, 0, CAST(N'11:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (2, 0, CAST(N'11:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (3, 0, CAST(N'11:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (4, 0, CAST(N'11:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (5, 0, CAST(N'12:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (6, 0, CAST(N'12:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (7, 0, CAST(N'12:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (8, 0, CAST(N'12:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (9, 0, CAST(N'13:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (10, 0, CAST(N'13:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (11, 0, CAST(N'13:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (12, 0, CAST(N'13:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (13, 0, CAST(N'14:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (14, 0, CAST(N'18:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (15, 0, CAST(N'18:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (16, 0, CAST(N'19:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (17, 0, CAST(N'19:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (18, 0, CAST(N'19:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (19, 0, CAST(N'19:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (20, 0, CAST(N'20:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (21, 0, CAST(N'20:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (22, 0, CAST(N'20:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (23, 0, CAST(N'20:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (24, 0, CAST(N'21:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (25, 0, CAST(N'21:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (26, 0, CAST(N'21:30:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (27, 0, CAST(N'21:45:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (28, 0, CAST(N'22:00:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (29, 0, CAST(N'22:15:00' AS Time))
INSERT [dbo].[TimeSlot] ([time_slot_id], [reservedPizza], [slot]) VALUES (30, 0, CAST(N'22:30:00' AS Time))
SET IDENTITY_INSERT [dbo].[TimeSlot] OFF
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Employee__5853029EFA040430]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[Employee] ADD UNIQUE NONCLUSTERED 
(
	[puuid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Employee__6E2DBEDEB2BDF244]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[Employee] ADD UNIQUE NONCLUSTERED 
(
	[password] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__MenuItem__72E12F1B02E54802]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[MenuItemCategory] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__OnlineOr__46596228C359BA6D]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[OnlineOrder] ADD UNIQUE NONCLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__Restaura__4659622816812330]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[RestaurantOrder] ADD UNIQUE NONCLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Role__72E12F1B6C75655F]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[Role] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__TimeSlot__32DD2E16D4B6DBB7]    Script Date: 04/08/2023 10:35:08 ******/
ALTER TABLE [dbo].[TimeSlot] ADD UNIQUE NONCLUSTERED 
(
	[slot] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Order] ADD  DEFAULT ((0)) FOR [is_to_Prepare]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [stock]
GO
ALTER TABLE [dbo].[RestaurantOrder] ADD  DEFAULT ((1)) FOR [order_step]
GO
ALTER TABLE [dbo].[TimeSlot] ADD  DEFAULT ((0)) FOR [reservedPizza]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[MenuItem]  WITH CHECK ADD FOREIGN KEY([category_id])
REFERENCES [dbo].[MenuItemCategory] ([id])
GO
ALTER TABLE [dbo].[MenuItem]  WITH CHECK ADD FOREIGN KEY([category_id])
REFERENCES [dbo].[MenuItemCategory] ([id])
GO
ALTER TABLE [dbo].[MenuItem_Order]  WITH CHECK ADD FOREIGN KEY([menu_item_id])
REFERENCES [dbo].[MenuItem] ([menu_item_id])
GO
ALTER TABLE [dbo].[MenuItem_Order]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([order_id])
GO
ALTER TABLE [dbo].[OnlineOrder]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([order_id])
GO
ALTER TABLE [dbo].[OnlineOrder]  WITH CHECK ADD FOREIGN KEY([time_slot_id])
REFERENCES [dbo].[TimeSlot] ([time_slot_id])
GO
ALTER TABLE [dbo].[Pizza]  WITH CHECK ADD FOREIGN KEY([menu_item_id])
REFERENCES [dbo].[MenuItem] ([menu_item_id])
GO
ALTER TABLE [dbo].[Product_MenuItem]  WITH CHECK ADD FOREIGN KEY([menu_item_id])
REFERENCES [dbo].[MenuItem] ([menu_item_id])
GO
ALTER TABLE [dbo].[Product_MenuItem]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([product_id])
GO
ALTER TABLE [dbo].[RestaurantOrder]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([order_id])
GO
EXEC sys.sp_addextendedproperty @name=N'Column_Description', @value=N'can you autogenerate this ?' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'password'
GO
EXEC sys.sp_addextendedproperty @name=N'Column_Description', @value=N'and autogenerate this ?' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Employee', @level2type=N'COLUMN',@level2name=N'puuid'
GO
EXEC sys.sp_addextendedproperty @name=N'Column_Description', @value=N'APPETIZER, MAIN_COURSE, DESSERT' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MenuItemCategory', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'Column_Description', @value=N'WAITER, COOK, MANAGER' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Role', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'Column_Description', @value=N'10:00, 10:15, 10:30...' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TimeSlot', @level2type=N'COLUMN',@level2name=N'slot'
GO
USE [master]
GO
ALTER DATABASE [PizzaHubBDD2] SET  READ_WRITE 
GO
