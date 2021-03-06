USE [master]
GO
/****** Object:  Database [LOBO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE DATABASE [LOBO]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LOBO', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\LOBO.mdf' , SIZE = 4160KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'LOBO_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\LOBO_log.ldf' , SIZE = 1088KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [LOBO] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LOBO].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LOBO] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LOBO] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LOBO] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LOBO] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LOBO] SET ARITHABORT OFF 
GO
ALTER DATABASE [LOBO] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LOBO] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [LOBO] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LOBO] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LOBO] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LOBO] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LOBO] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LOBO] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LOBO] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LOBO] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LOBO] SET  ENABLE_BROKER 
GO
ALTER DATABASE [LOBO] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LOBO] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LOBO] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LOBO] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LOBO] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LOBO] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LOBO] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LOBO] SET RECOVERY FULL 
GO
ALTER DATABASE [LOBO] SET  MULTI_USER 
GO
ALTER DATABASE [LOBO] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LOBO] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LOBO] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LOBO] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [LOBO]
GO
/****** Object:  StoredProcedure [dbo].[SP_ACCEDER]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ACCEDER](@Usuario VARCHAR(45),@Contrasena VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.Usuario AS USUARIO, U.Contrasena AS CONTRASENA FROM Usuarios AS U 
	WHERE U.Estatus IN('ACTIVO') AND  U.Usuario  = @Usuario AND U.Contrasena = @Contrasena;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_CATALOGO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_CATALOGO](@FieldId VARCHAR(45),@IValue INT, @SValue VARCHAR(50),@Special VARCHAR(50),@Valor_Num FLOAT,@Valor_Text VARCHAR(85))
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO catalogos ([FieldId],[IValue],[SValue],[Special],[Valor_Num],[Valor_Text],[Estatus]) VALUES (@FieldId,@IValue,@SValue,@Special,@Valor_Num,@Valor_Text,'ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_COMBINACION]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_COMBINACION](
@Clave VARCHAR(15),
@Descripcion VARCHAR(100), 
@Linea INT, 
@Estilo INT
)
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO Combinaciones([Clave],[Descripcion],[Linea],[Estilo],[Estatus]) 
						VALUES (@Clave, @Descripcion, @Linea,@Estilo,'ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_ESTILO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_ESTILO](
@Linea INT, @Clave VARCHAR(45), @Descripcion VARCHAR(50), 
@Familia INT, @Serie INT, @Horma INT, 
@Genero VARCHAR(45), @Foto VARCHAR(999), @Estatus VARCHAR(45),@Desperdicio VARCHAR(45), 
	@Liberado VARCHAR(5), @Herramental VARCHAR(5), @Maquila INT, @Notas VARCHAR(50), @Ano INT, 
	@Temporada INT, @PuntoCentral INT, @Tipo INT, @MaquilaPlantilla VARCHAR(45), @TipoDeConstruccion VARCHAR(45),@Registro VARCHAR(25))
AS
BEGIN
SET NOCOUNT ON;

	INSERT INTO Estilos ([Linea], [Clave], [Descripcion], [Familia], 
	[Serie], [Horma], [Genero], [Foto], [Estatus], 
	[Desperdicio], [Liberado], [Herramental], [Maquila], [Notas], 
	[Ano], [Temporada], [PuntoCentral], [Tipo], [MaquilaPlantilla], 
	[TipoDeConstruccion], [Registro]) 
	VALUES (@Linea, @Clave, @Descripcion, @Familia, @Serie, 
	@Horma, @Genero, @Foto, @Estatus, @Desperdicio, 
	@Liberado, @Herramental, @Maquila, @Notas, @Ano, 
	@Temporada, @PuntoCentral, @Tipo, @MaquilaPlantilla, @TipoDeConstruccion, @Registro);

END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_FRACCION]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_FRACCION](
@Clave VARCHAR(15),
@Descripcion VARCHAR(100), 
@DepartamentoCat INT
)
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO Fracciones([Clave],[Descripcion],[DepartamentoCat],[Estatus]) 
						VALUES (@Clave, @Descripcion, @DepartamentoCat,'ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_LINEA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_LINEA](
@Clave VARCHAR(45),
@Descripcion VARCHAR(90), 
@TipoEstiloCat INT, 
@Ano VARCHAR(45),
@TemporadaCat INT
)
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO lineas ([Clave],[Descripcion],[TipoEstiloCat],[Ano],[TemporadaCat],[Estatus]) 
						VALUES (@Clave, @Descripcion, @TipoEstiloCat,@Ano,@TemporadaCat,'ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_MAQUILA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_MAQUILA](
@Clave VARCHAR(8),
@Nombre VARCHAR(80), 
@Direccion VARCHAR(120), 
@Telefono VARCHAR(45),
@Contacto VARCHAR(45)
)
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO maquilas ([Clave],[Nombre],[Direccion],[Telefono],[Contacto],[Estatus]) 
						VALUES (@Clave, @Nombre, @Direccion,@Telefono,@Contacto,'ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_MATERIAL]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_MATERIAL](@Material VARCHAR(45), 
@Departamento INT, @Familia INT, @Descripcion VARCHAR(45), 
@UnidadCompra INT, @UnidadConsumo INT, @Tipo VARCHAR(45),@Minimo FLOAT, 
	@Maximo FLOAT, @PrecioLista FLOAT, @PrecioTope FLOAT, @FechaUltimoInventario VARCHAR(50), @Existencia FLOAT, 
	@Estatus VARCHAR(10))
AS
BEGIN
SET NOCOUNT ON;

INSERT INTO [dbo].[Materiales]
           ([Material]            ,[Departamento]           ,[Familia]           ,[Descripcion]
           ,[UnidadCompra]           ,[UnidadConsumo]           ,[Tipo]           ,[Minimo]
           ,[Maximo]           ,[PrecioLista]           ,[PrecioTope]           ,[FechaUltimoInventario]
           ,[Existencia]           ,[Estatus])
     VALUES
           (@Material            ,@Departamento
           ,@Familia           ,@Descripcion
           ,@UnidadCompra           ,@UnidadConsumo
           ,@Tipo           ,@Minimo
           ,@Maximo           ,@PrecioLista
           ,@PrecioTope           ,@FechaUltimoInventario
           ,@Existencia           ,@Estatus); 

END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_MODULO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_MODULO](@Modulo VARCHAR(45), @Registro VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO Modulos ([Modulo],[Estatus],[Registro]) VALUES (@Modulo,'ACTIVO',@Registro);
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_PERMISO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_PERMISO](
@Usuario VARCHAR(45), @Modulo VARCHAR(45), @Ver INT, 
@Crear INT, @Modificar INT, @Eliminar INT, 
@Consultar INT, @Reportes INT, @Buscar INT,@Registro VARCHAR(25))
AS
BEGIN

DECLARE @IDUSUARIO INT, @IDMODULO INT

SET NOCOUNT ON;
	SET  @IDUSUARIO =(SELECT U.ID FROM Usuarios AS U WHERE U.Usuario = @Usuario);
	SET  @IDMODULO =(SELECT M.ID FROM Modulos AS M WHERE M.Modulo = @Modulo);
    INSERT INTO Permisos ([IdUsuario], [UsuarioT], [IdModulo], [ModuloT], [Ver], [Crear], [Modificar], 
    [Eliminar], [Consultar], [Reportes], [Buscar], [Estatus], [Registro]) 
	VALUES (@IDUSUARIO, @Usuario, @IDMODULO, @Modulo, @Ver, @Crear, @Modificar, @Eliminar, @Consultar, @Reportes, @Buscar, 'ACTIVO', @Registro);
END


GO
/****** Object:  StoredProcedure [dbo].[SP_AGREGAR_USUARIO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_AGREGAR_USUARIO](@Usuario VARCHAR(45), @Contrasena VARCHAR(45), @Correo VARCHAR(45), @Registro VARCHAR(45))
AS
BEGIN
	SET NOCOUNT ON;
INSERT INTO usuarios ([Usuario],[Contrasena],[Correo],[Estatus],[Registro]) VALUES (@Usuario,@Contrasena,@Correo,'ACTIVO',@Registro);
END

GO
/****** Object:  StoredProcedure [dbo].[SP_BUSCAR_CATALOGO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_BUSCAR_CATALOGO](@Valor_Text VARCHAR(85),@SValue VARCHAR(50))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.SValue AS Nombre, C.Valor_Num AS Valor,  C.Valor_Text AS Descripcion FROM Catalogos AS C WHERE C.Estatus IN('ACTIVO')
	AND C.Valor_Text LIKE CONCAT('%',@Valor_Text,'%') AND C.SValue LIKE CONCAT('%',@SValue,'%');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_BUSCAR_LINEA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_BUSCAR_LINEA](@MAX INT, @Clave VARCHAR(45),@Descripcion VARCHAR(90))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) L.ID AS ID, L.Clave AS CLAVE, L.Descripcion AS DESCRIPCION, L.Ano AS ANO, L.Estatus AS ESTATUS  FROM Lineas AS L 
	WHERE L.Clave LIKE CONCAT('%',@Clave,'%') AND L.Descripcion LIKE CONCAT('%',@Descripcion,'%');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_BUSCAR_MAQUILA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_BUSCAR_MAQUILA](@MAX INT, @Clave VARCHAR(45),@Nombre VARCHAR(80))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Clave AS CLAVE, M.Nombre AS NOMBRE,M.Estatus AS ESTATUS FROM Maquilas AS M 
	WHERE M.Clave LIKE CONCAT('%',@Clave,'%') AND M.Nombre LIKE CONCAT('%',@Nombre,'%') ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_CATALOGO_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_CATALOGO_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 C.IValue AS Orden, C.SValue AS Nombre,  C.Valor_Text AS Descripcion, C.Valor_Num AS Valor, C.Special AS Special ,C.Estatus AS Estatus FROM Catalogos AS C
    WHERE C.ID = @IDX  ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_CATALOGOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_CATALOGOS](@MAX INT,@FIELD_ID VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) C.ID AS ID, C.IValue AS Orden, C.SValue AS Nombre,C.Valor_Text AS Descripcion, C.Valor_Num AS Valor   FROM Catalogos AS C WHERE C.FieldId = @FIELD_ID AND C.Estatus IN('ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_COMBINACION_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_COMBINACION_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 C.ID AS ID, C.Clave AS CLAVE, C.DESCRIPCION AS DESCRIPCION, L.Clave  AS LINEA, E.Clave AS ESTILO,C.Estatus AS ESTATUS
    FROM Combinaciones AS C
	LEFT JOIN Lineas L ON L.ID = C.Linea
	LEFT JOIN Estilos E ON E.ID = C.Estilo
    WHERE C.ID = @IDX AND C.Estatus IN('ACTIVO','INACTIVO') ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_COMBINACIONES]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_COMBINACIONES](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX) C.ID AS ID, C.Clave AS CLAVE, C.Descripcion AS DESCRIPCION, C.Estatus AS ESTATUS FROM Combinaciones AS C WHERE C.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_CATALOGO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_CATALOGO](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE catalogos
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_COMBINACION]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_COMBINACION](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Combinaciones
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_ESTILO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_ESTILO](@IDX INT)
AS
BEGIN
SET NOCOUNT ON;
	UPDATE estilos
		SET [Estatus] = 'INACTIVO' WHERE [ID] = @IDX;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_FRACCION]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_FRACCION](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Fracciones
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_LINEA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_LINEA](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Lineas
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_MAQUILA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_MAQUILA](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE maquilas
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_MATERIAL]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_MATERIAL](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Material
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_MODULO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_MODULO](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Modulos
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_PERMISO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_PERMISO](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Permisos
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_USUARIO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ELIMINAR_USUARIO](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE usuarios
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ESTILO_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ESTILO_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
SELECT 
	E.ID, L.Descripcion, E.Clave, E.Descripcion, F.SValue AS FAMILIA, 
	S.SValue AS SERIE, H.SValue AS HORMA, E.Genero, E.Foto, E.Estatus, 
	E.Desperdicio, E.Liberado, E.Herramental, M.SValue  AS MAQUILA, E.Notas, 
	E.Ano, T.SValue AS TEMPORADA, E.PuntoCentral, TE.SValue  AS TIPO_ESTILO, E.MaquilaPlantilla, 
	E.TipoDeConstruccion, E.Registro AS REGISTRO 
    FROM Estilos AS E 
    LEFT JOIN Lineas AS L ON E.Linea = L.ID 
    LEFT JOIN Catalogos AS F ON E.Familia = F.ID AND F.FieldId LIKE 'FAMILIAS'
    LEFT JOIN Catalogos AS S ON E.Serie = S.ID  AND S.FieldId LIKE 'SERIES'
    LEFT JOIN Catalogos AS H ON E.Horma = H.ID  AND H.FieldId LIKE 'HORMAS'
    LEFT JOIN Catalogos AS M ON E.Maquila = M.ID  AND M.FieldId LIKE 'MAQUILAS'
    LEFT JOIN Catalogos AS T ON E.Temporada = T.ID  AND T.FieldId LIKE 'TEMPORADAS'
    LEFT JOIN Catalogos AS TE ON E.Tipo = TE.ID  AND TE.FieldId LIKE 'TIPOS ESTILO'
    WHERE E.ID = @IDX AND E.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ESTILOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ESTILOS](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX)
	E.ID, L.Descripcion AS LINEA, E.Clave AS CLAVE, E.Descripcion AS DESCRIPCION, E.Estatus AS ESTATUS,  E.Registro AS REGISTRO 
    FROM Estilos AS E 
    INNER JOIN Lineas AS L ON E.Linea = L.ID
    WHERE E.Estatus IN('ACTIVO','INACTIVO') ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_FRACCION_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_FRACCION_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 C.ID AS ID, C.Clave AS CLAVE, 
	C.DESCRIPCION AS DESCRIPCION, 
	CAT.SValue  AS DEPARTAMENTO,
	C.Estatus AS ESTATUS
    FROM Fracciones AS C
	LEFT JOIN Catalogos CAT ON CAT.ID = C.DepartamentoCat
    WHERE C.ID = @IDX AND C.Estatus IN('ACTIVO','INACTIVO') ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_FRACCIONES]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_FRACCIONES](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX) C.ID AS ID, C.Clave AS CLAVE, C.Descripcion AS DESCRIPCION, C.Estatus AS ESTATUS FROM Fracciones AS C WHERE C.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_LINEA_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_LINEA_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 L.ID AS ID, L.Clave AS CLAVE, L.DESCRIPCION AS DESCRIPCION, TE.SValue  AS TipoEstiloCat, L.Ano, TEMP.SValue AS TEMPORADA,
		L.Estatus AS ESTATUS
    FROM Lineas AS L
	LEFT JOIN Catalogos TE ON TE.ID = L.TipoEstiloCat AND TE.FieldId IN ('TIPOS ESTILO')
	LEFT JOIN Catalogos TEMP ON TEMP.ID = L.TemporadaCat AND TEMP.FieldId IN ('TEMPORADAS')
    WHERE L.ID = @IDX AND L.Estatus IN('ACTIVO','INACTIVO') ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_LINEAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_LINEAS](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX) L.ID AS ID, L.Clave AS CLAVE, L.Descripcion AS DESCRIPCION, L.Ano AS ANO, L.Estatus AS ESTATUS FROM Lineas AS L WHERE L.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MAQUILA_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MAQUILA_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 M.ID AS ID, M.Clave AS CLAVE, M.NOMBRE AS NOMBRE, M.Direccion  AS DIRECCION, M.Telefono AS TELEFONO, M.Contacto AS CONTACTO,
		M.Estatus AS ESTATUS
    FROM MAQUILAS AS M
    WHERE M.ID = @IDX AND M.Estatus IN('ACTIVO','INACTIVO') ;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MAQUILAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MAQUILAS](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Clave AS CLAVE, M.Nombre AS NOMBRE,M.Estatus AS ESTATUS FROM Maquilas AS M WHERE M.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MATERIAL_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MATERIAL_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 M.ID AS ID, M.Material AS MATERIAL, D.SValue AS DEPARTAMENTO, F.SValue AS FAMILIA, M.Descripcion AS DESCRIPCION,
        U.SValue AS UNIDAD_DE_COMPRA, UU.SValue AS UNIDAD_DE_CONSUMO, M.Tipo AS TIPO, M.Minimo AS MINIMO, M.Maximo AS MAXIMO,
        M.PrecioLista AS PRECIO_DE_LISTA, M.PrecioTope AS PRECIO_TOPE, M.FechaUltimoInventario AS FECHA_ULTIMO_INVENTARIO, 
        M.Existencia AS EXISTENCIA, M.Estatus AS ESTATUS
        FROM [Materiales] AS M 
        LEFT JOIN Catalogos AS D ON M.[Departamento] = D.ID
        LEFT JOIN Catalogos AS F ON M.[Familia] = F.ID
        LEFT JOIN Catalogos AS U ON M.UnidadCompra = U.ID
        LEFT JOIN Catalogos AS UU ON M.UnidadConsumo = UU.ID
        WHERE M.ID = @IDX AND M.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MATERIALES]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MATERIALES](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Descripcion AS DESCRIPCION, M.Estatus AS ESTATUS
	FROM Materiales AS M
    WHERE M.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_CATALOGO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_CATALOGO](@IDX INT,@IValue INT, @SValue VARCHAR(50), @Valor_Num FLOAT,@Valor_Text VARCHAR(85),@Special VARCHAR(50),@Estatus VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE catalogos
	SET [IValue] = @IValue,[SValue] = @SValue, [Valor_Num] =@Valor_Num, [Valor_Text] = @Valor_Text ,[Special] = @Special , [Estatus] = @Estatus 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_COMBINACION]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_COMBINACION](@IDX INT, @Clave VARCHAR(15), @Descripcion VARCHAR(100), @Linea INT,@Estilo INT, @Estatus VARCHAR(25))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Combinaciones
	SET [Clave] = @Clave, [Descripcion] = @Descripcion, [Linea] = @Linea,[Estilo] = @Estilo,[Estatus] = @Estatus
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_ESTILO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_MODIFICAR_ESTILO](@IDX INT,
@Linea INT, @Clave VARCHAR(45), @Descripcion VARCHAR(50), 
@Familia INT, @Serie INT, @Horma INT, 
@Genero VARCHAR(45), @Foto VARCHAR(999), @Estatus VARCHAR(45),@Desperdicio VARCHAR(45), 
	@Liberado VARCHAR(5), @Herramental VARCHAR(5), @Maquila INT, @Notas VARCHAR(50), @Ano INT, 
	@Temporada INT, @PuntoCentral INT, @Tipo INT, @MaquilaPlantilla VARCHAR(45), @TipoDeConstruccion VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON;
    UPDATE estilos
	SET [Linea] = @Linea,
		[Clave] = @Clave ,
		[Descripcion] = @Descripcion ,
		[Familia] = @Familia ,
		[Serie] = @Serie ,
		[Horma] = @Horma ,
		[Genero] = @Genero ,
		[Foto] = @Foto ,
		[Estatus] = @Estatus ,
		[Desperdicio] = @Desperdicio ,
		[Liberado] = @Liberado ,
		[Herramental] = @Herramental ,
		[Maquila] = @Maquila ,
		[Notas] = @Notas ,
		[Ano] = @Ano ,
		[Temporada] = @Temporada ,
		[PuntoCentral] = @PuntoCentral ,
		[Tipo] = @Tipo ,
		[MaquilaPlantilla] = @MaquilaPlantilla ,
		[TipoDeConstruccion] = @TipoDeConstruccion 
	WHERE [ID] = @IDX;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_FRACCION]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_FRACCION](
@IDX INT, @Clave VARCHAR(15), 
@Descripcion VARCHAR(100), 
@DepartamentoCat INT,
@Estatus VARCHAR(25))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Fracciones
	SET [Clave] = @Clave, [Descripcion] = @Descripcion, [DepartamentoCat] = @DepartamentoCat,[Estatus] = @Estatus
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_LINEA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_LINEA](@IDX INT, @Clave VARCHAR(45), @Descripcion VARCHAR(90), @TipoEstiloCat INT,@Ano VARCHAR(45), @TemporadaCat INT, @Estatus VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Lineas
	SET [Clave] = @Clave, [Descripcion] = @Descripcion, [TipoEstiloCat] = @TipoEstiloCat, [Ano] = @Ano,[TemporadaCat] = @TemporadaCat,[Estatus] = @Estatus
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_MAQUILA]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_MAQUILA](
@IDX INT, 
@Clave VARCHAR(8),
@Nombre VARCHAR(80), 
@Direccion VARCHAR(120), 
@Telefono VARCHAR(45),
@Contacto VARCHAR(45),
@Estatus VARCHAR(45)
)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE maquilas
	SET [Clave] = @Clave, [Nombre] = @Nombre, [Direccion] = @Direccion, [Telefono] = @Telefono,[Contacto] = @Contacto,[Estatus] = @Estatus
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_MATERIAL]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE  PROCEDURE [dbo].[SP_MODIFICAR_MATERIAL](@ID INT, @Material VARCHAR(45), 
@Departamento INT, @Familia INT, @Descripcion VARCHAR(45), 
@UnidadCompra INT, @UnidadConsumo INT, @Tipo VARCHAR(45),@Minimo FLOAT, 
	@Maximo FLOAT, @PrecioLista FLOAT, @PrecioTope FLOAT, @FechaUltimoInventario VARCHAR(50), @Existencia FLOAT, 
	@Estatus VARCHAR(10))
AS
BEGIN
SET NOCOUNT ON;

 
UPDATE [dbo].[Materiales]
   SET [Material] = @Material 
      ,[Departamento] = @Departamento
      ,[Familia] = @Familia
      ,[Descripcion] = @Descripcion
      ,[UnidadCompra] = @UnidadCompra
      ,[UnidadConsumo] = @UnidadConsumo
      ,[Tipo] = @Tipo
      ,[Minimo] = @Minimo
      ,[Maximo] = @Maximo
      ,[PrecioLista] = @PrecioLista
      ,[PrecioTope] = @PrecioTope
      ,[FechaUltimoInventario] = @FechaUltimoInventario
      ,[Existencia] = @Existencia
      ,[Estatus] = @Estatus
 WHERE ID = @ID;

END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_MODULO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_MODULO](@IDX INT, @Modulo VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Modulos
	SET [Modulo] = @Modulo 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_PERMISO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_PERMISO](@IDX INT, @Ver INT, 
@Crear INT, @Modificar INT, @Eliminar INT, 
@Consultar INT, @Reportes INT, @Buscar INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Permisos
	SET [Ver]=@Ver, [Crear]=@Crear, [Modificar]=@Modificar, 
    [Eliminar]=@Eliminar, [Consultar]=@Consultar, [Reportes]=@Reportes, [Buscar]=@Buscar
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODIFICAR_USUARIO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODIFICAR_USUARIO](@IDX INT, @Usuario VARCHAR(45), @Contrasena VARCHAR(45), @Correo VARCHAR(45), @Tipo VARCHAR(65))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE usuarios
	SET [Usuario] = @Usuario, [Contrasena] =@Contrasena, [Correo] = @Correo ,[Tipo] = @Tipo 
	WHERE [ID] = @IDX; 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODULO_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODULO_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 M.ID AS ID, M.MODULO AS MODULO, M.Estatus AS ESTATUS, M.Registro AS REGISTRO FROM Modulos AS M
    WHERE M.ID = @IDX AND M.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_MODULOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MODULOS](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Modulo AS MODULO, M.Estatus AS ESTATUS, M.Registro AS REGISTRO FROM Modulos AS M WHERE M.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_DEPARTAMENTOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_DEPARTAMENTOS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT D.ID AS ID, D.SValue AS DEPARTAMENTOS FROM CATALOGOS AS D WHERE D.Estatus IN('ACTIVO') AND D.FieldId LIKE 'DEPARTAMENTOS';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_ESTILOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_ESTILOS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT E.ID AS ID, E.Descripcion AS LINEA FROM Estilos AS E WHERE E.Estatus IN('ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_FAMILIAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_FAMILIAS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT F.ID AS ID, F.SValue AS FAMILIA FROM CATALOGOS AS F WHERE F.Estatus IN('ACTIVO') AND F.FieldId LIKE 'FAMILIAS';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_HORMAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_HORMAS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS HORMA FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'HORMAS';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_LINEAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_LINEAS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT L.ID AS ID, L.Descripcion AS LINEA FROM Lineas AS L WHERE L.Estatus IN('ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_MAQUILAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_MAQUILAS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.Nombre AS MAQUILA FROM Maquilas AS C WHERE C.Estatus IN('ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_MODULOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_MODULOS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT M.ID AS ID, M.Modulo AS MODULO FROM Modulos AS M WHERE M.Estatus IN('ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_SERIES]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_SERIES]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS SERIE FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'SERIES';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_TEMPORADAS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_TEMPORADAS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS TEMPORADA FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'TEMPORADAS';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_TIPOS_ESTILO]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_TIPOS_ESTILO]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS TIPO FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'TIPOS ESTILO';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_UNIDADES]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_UNIDADES]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.ID AS ID, U.SValue AS UNIDAD FROM CATALOGOS AS U WHERE U.Estatus IN('ACTIVO') AND U.FieldId LIKE 'UNIDADES';
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_USUARIOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_USUARIOS]
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.ID AS ID, U.Usuario AS USUARIO FROM Usuarios AS U WHERE U.Estatus IN('ACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_PERMISO_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_PERMISO_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 P.ID AS ID, P.UsuarioT AS USUARIO,P.ModuloT AS MODULO, P.Ver, P.Crear, P.Modificar, P.Eliminar, P.Consultar, P.Reportes, P.Buscar, 
    P.Registro AS REGISTRO FROM Permisos AS P
    WHERE P.ID = @IDX AND P.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_PERMISOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_PERMISOS](@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX)
	P.ID AS ID, P.UsuarioT AS USUARIO, P.ModuloT AS MODULO, 
    P.Ver AS VER, P.Crear AS CREAR, P.Modificar AS MODIFICAR, P.Eliminar AS ELIMINAR, P.Consultar AS CONSULTAR, 
    P.Reportes AS REPORTES, P.Buscar AS BUSCAR, P.Estatus  AS ESTATUS, P.Registro AS REGISTRO 
    FROM Permisos AS P 
    WHERE P.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_USUARIO_X_ID]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_USUARIO_X_ID](@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 U.ID AS ID, U.Usuario AS USUARIO, U.Contrasena AS CONTRASENA, U.Correo AS CORREO, U.Estatus AS ESTATUS, U.Tipo AS TIPO FROM Usuarios AS U
    WHERE U.ID = @IDX AND U.Estatus IN('ACTIVO','INACTIVO');
END

GO
/****** Object:  StoredProcedure [dbo].[SP_USUARIOS]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_USUARIOS] (@MAX INT)
	
AS
BEGIN
	SET NOCOUNT ON;
SELECT TOP (@MAX) U.ID AS ID, U.Usuario AS USUARIO, U.Tipo AS TIPO, U.Estatus AS ESTATUS,  U.Registro AS REGISTRO 
FROM Usuarios AS U 
WHERE U.Estatus IN ('ACTIVO','INACTIVO')
END

GO
/****** Object:  Table [dbo].[Catalogos]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Catalogos](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[FieldId] [varchar](45) NULL,
	[IValue] [int] NULL,
	[SValue] [varchar](50) NULL,
	[Special] [varchar](50) NULL,
	[Valor_Num] [float] NULL,
	[Valor_Text] [varchar](85) NULL,
	[Estatus] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Combinaciones]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Combinaciones](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Clave] [varchar](15) NULL,
	[Descripcion] [varchar](100) NULL,
	[Linea] [int] NULL,
	[Estilo] [int] NULL,
	[Estatus] [varchar](25) NULL,
 CONSTRAINT [PK_Combinaciones] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Empresas]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Empresas](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](200) NULL,
	[NombreComercial] [varchar](200) NULL,
	[Razon] [varchar](245) NULL,
	[RFC] [varchar](45) NULL,
	[Tamanio] [varchar](45) NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
	[Usuario] [int] NULL,
	[UsuarioT] [varchar](145) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Estilos]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Estilos](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Linea] [int] NULL,
	[Clave] [varchar](45) NULL,
	[Descripcion] [varchar](50) NULL,
	[Familia] [int] NULL,
	[Serie] [int] NULL,
	[Horma] [int] NULL,
	[Genero] [varchar](15) NULL,
	[Foto] [varchar](999) NULL,
	[Estatus] [varchar](25) NULL,
	[Desperdicio] [varchar](45) NULL,
	[Liberado] [varchar](3) NULL,
	[Herramental] [varchar](3) NULL,
	[Maquila] [int] NULL,
	[Notas] [varchar](70) NULL,
	[Ano] [int] NULL,
	[Temporada] [int] NULL,
	[PuntoCentral] [int] NULL,
	[Tipo] [int] NULL,
	[MaquilaPlantilla] [varchar](45) NULL,
	[TipoDeConstruccion] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fracciones]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fracciones](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Clave] [varchar](15) NULL,
	[Descripcion] [varchar](100) NULL,
	[DepartamentoCat] [int] NULL,
	[Estatus] [varchar](25) NULL,
 CONSTRAINT [PK_Fracciones] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Lineas]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Lineas](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Clave] [varchar](45) NULL,
	[Descripcion] [varchar](90) NULL,
	[Ano] [varchar](45) NULL,
	[TemporadaCat] [int] NULL,
	[Estatus] [varchar](45) NULL,
	[TipoEstiloCat] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Logs]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Logs](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Accion] [varchar](150) NULL,
	[Usuario] [varchar](99) NULL,
	[Modulo] [varchar](99) NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Maquilas]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Maquilas](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Clave] [varchar](8) NULL,
	[Nombre] [varchar](80) NULL,
	[Direccion] [varchar](120) NULL,
	[Telefono] [varchar](45) NULL,
	[Contacto] [varchar](45) NULL,
	[Estatus] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Materiales]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[Materiales](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Material] [varchar](15) NULL,
	[Departamento] [int] NULL,
	[Familia] [int] NULL,
	[Descripcion] [varchar](70) NULL,
	[UnidadCompra] [int] NULL,
	[UnidadConsumo] [int] NULL,
	[Tipo] [varchar](50) NULL,
	[Minimo] [float] NULL,
	[Maximo] [float] NULL,
	[PrecioLista] [float] NULL,
	[PrecioTope] [float] NULL,
	[FechaUltimoInventario] [varchar](15) NULL,
	[Existencia] [float] NULL,
	[Estatus] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Modulos]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Modulos](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Modulo] [varchar](95) NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Permisos]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Permisos](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IdUsuario] [int] NULL,
	[UsuarioT] [varchar](99) NULL,
	[IdModulo] [int] NULL,
	[ModuloT] [varchar](99) NULL,
	[Ver] [int] NULL,
	[Crear] [int] NULL,
	[Modificar] [int] NULL,
	[Eliminar] [int] NULL,
	[Consultar] [int] NULL,
	[Reportes] [int] NULL,
	[Buscar] [int] NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PermisosXUsuario]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PermisosXUsuario](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ver] [int] NULL,
	[Consultar] [int] NULL,
	[Agregar] [int] NULL,
	[Modificar] [int] NULL,
	[Eliminar] [int] NULL,
	[Exportar] [int] NULL,
	[Tipo] [varchar](45) NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
	[FechaUltimaModificacion] [varchar](45) NULL,
	[Usuario] [int] NULL,
	[UsuarioT] [varchar](95) NULL,
	[Modulo] [int] NULL,
	[ModuloT] [varchar](95) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Usuarios](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Usuario] [varchar](45) NULL,
	[Contrasena] [varchar](45) NULL,
	[Correo] [varchar](45) NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
	[Tipo] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UsuariosXEmpresa]    Script Date: 01/03/2018 12:29:59 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UsuariosXEmpresa](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Usuario] [int] NULL,
	[Empresa] [int] NULL,
	[Estatus] [varchar](45) NULL,
	[Registro] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Index [fk_Estilos_CATALOGOS1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_CATALOGOS1_idx] ON [dbo].[Estilos]
(
	[Familia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Estilos_CATALOGOS2_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_CATALOGOS2_idx] ON [dbo].[Estilos]
(
	[Serie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Estilos_CATALOGOS3_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_CATALOGOS3_idx] ON [dbo].[Estilos]
(
	[Horma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Estilos_CATALOGOS4_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_CATALOGOS4_idx] ON [dbo].[Estilos]
(
	[Maquila] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Estilos_CATALOGOS5_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_CATALOGOS5_idx] ON [dbo].[Estilos]
(
	[Temporada] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Estilos_CATALOGOS6_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_CATALOGOS6_idx] ON [dbo].[Estilos]
(
	[Tipo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Estilos_Lineas1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Estilos_Lineas1_idx] ON [dbo].[Estilos]
(
	[Linea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Permisos_Modulos1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Permisos_Modulos1_idx] ON [dbo].[Permisos]
(
	[IdModulo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_Permisos_Usuarios1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_Permisos_Usuarios1_idx] ON [dbo].[Permisos]
(
	[IdUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_PermisosXUsuario_Modulos1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_PermisosXUsuario_Modulos1_idx] ON [dbo].[PermisosXUsuario]
(
	[Modulo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_PermisosXUsuario_Usuarios_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_PermisosXUsuario_Usuarios_idx] ON [dbo].[PermisosXUsuario]
(
	[Usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_UsuariosXEmpresa_Empresas1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_UsuariosXEmpresa_Empresas1_idx] ON [dbo].[UsuariosXEmpresa]
(
	[Empresa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fk_UsuariosXEmpresa_Usuarios1_idx]    Script Date: 01/03/2018 12:29:59 p. m. ******/
CREATE NONCLUSTERED INDEX [fk_UsuariosXEmpresa_Usuarios1_idx] ON [dbo].[UsuariosXEmpresa]
(
	[Usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Catalogos] ADD  DEFAULT ((0.00)) FOR [Valor_Num]
GO
ALTER TABLE [dbo].[Modulos] ADD  DEFAULT ('ACTIVO') FOR [Estatus]
GO
ALTER TABLE [dbo].[Permisos] ADD  DEFAULT ('ACTIVO') FOR [Estatus]
GO
ALTER TABLE [dbo].[Combinaciones]  WITH CHECK ADD  CONSTRAINT [FK_Combinaciones_Estilos] FOREIGN KEY([Estilo])
REFERENCES [dbo].[Estilos] ([ID])
GO
ALTER TABLE [dbo].[Combinaciones] CHECK CONSTRAINT [FK_Combinaciones_Estilos]
GO
ALTER TABLE [dbo].[Combinaciones]  WITH CHECK ADD  CONSTRAINT [FK_Combinaciones_Lineas] FOREIGN KEY([Linea])
REFERENCES [dbo].[Lineas] ([ID])
GO
ALTER TABLE [dbo].[Combinaciones] CHECK CONSTRAINT [FK_Combinaciones_Lineas]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_CATALOGOS1] FOREIGN KEY([Familia])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_CATALOGOS1]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_CATALOGOS2] FOREIGN KEY([Serie])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_CATALOGOS2]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_CATALOGOS3] FOREIGN KEY([Horma])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_CATALOGOS3]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_CATALOGOS4] FOREIGN KEY([Maquila])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_CATALOGOS4]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_CATALOGOS5] FOREIGN KEY([Temporada])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_CATALOGOS5]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_CATALOGOS6] FOREIGN KEY([Tipo])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_CATALOGOS6]
GO
ALTER TABLE [dbo].[Estilos]  WITH CHECK ADD  CONSTRAINT [fk_Estilos_Lineas1] FOREIGN KEY([Linea])
REFERENCES [dbo].[Lineas] ([ID])
GO
ALTER TABLE [dbo].[Estilos] CHECK CONSTRAINT [fk_Estilos_Lineas1]
GO
ALTER TABLE [dbo].[Materiales]  WITH CHECK ADD  CONSTRAINT [FK_UnidadCompra] FOREIGN KEY([UnidadCompra])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Materiales] CHECK CONSTRAINT [FK_UnidadCompra]
GO
ALTER TABLE [dbo].[Materiales]  WITH CHECK ADD  CONSTRAINT [FK_UnidadConsumo] FOREIGN KEY([UnidadConsumo])
REFERENCES [dbo].[Catalogos] ([ID])
GO
ALTER TABLE [dbo].[Materiales] CHECK CONSTRAINT [FK_UnidadConsumo]
GO
ALTER TABLE [dbo].[Permisos]  WITH CHECK ADD  CONSTRAINT [fk_Permisos_Modulos1] FOREIGN KEY([IdModulo])
REFERENCES [dbo].[Modulos] ([ID])
GO
ALTER TABLE [dbo].[Permisos] CHECK CONSTRAINT [fk_Permisos_Modulos1]
GO
ALTER TABLE [dbo].[Permisos]  WITH CHECK ADD  CONSTRAINT [fk_Permisos_Usuarios1] FOREIGN KEY([IdUsuario])
REFERENCES [dbo].[Usuarios] ([ID])
GO
ALTER TABLE [dbo].[Permisos] CHECK CONSTRAINT [fk_Permisos_Usuarios1]
GO
ALTER TABLE [dbo].[PermisosXUsuario]  WITH CHECK ADD  CONSTRAINT [fk_PermisosXUsuario_Modulos1] FOREIGN KEY([Modulo])
REFERENCES [dbo].[Modulos] ([ID])
GO
ALTER TABLE [dbo].[PermisosXUsuario] CHECK CONSTRAINT [fk_PermisosXUsuario_Modulos1]
GO
ALTER TABLE [dbo].[PermisosXUsuario]  WITH CHECK ADD  CONSTRAINT [fk_PermisosXUsuario_Usuarios] FOREIGN KEY([Usuario])
REFERENCES [dbo].[Usuarios] ([ID])
GO
ALTER TABLE [dbo].[PermisosXUsuario] CHECK CONSTRAINT [fk_PermisosXUsuario_Usuarios]
GO
ALTER TABLE [dbo].[UsuariosXEmpresa]  WITH CHECK ADD  CONSTRAINT [fk_UsuariosXEmpresa_Empresas1] FOREIGN KEY([Empresa])
REFERENCES [dbo].[Empresas] ([ID])
GO
ALTER TABLE [dbo].[UsuariosXEmpresa] CHECK CONSTRAINT [fk_UsuariosXEmpresa_Empresas1]
GO
ALTER TABLE [dbo].[UsuariosXEmpresa]  WITH CHECK ADD  CONSTRAINT [fk_UsuariosXEmpresa_Usuarios1] FOREIGN KEY([Usuario])
REFERENCES [dbo].[Usuarios] ([ID])
GO
ALTER TABLE [dbo].[UsuariosXEmpresa] CHECK CONSTRAINT [fk_UsuariosXEmpresa_Usuarios1]
GO
USE [master]
GO
ALTER DATABASE [LOBO] SET  READ_WRITE 
GO



-- =========================================
-- Create table MaterialesXCombinacion
-- =========================================
USE LOBO;
GO

IF OBJECT_ID('dbo.MaterialesXCombinacion', 'U') IS NOT NULL
  DROP TABLE dbo.MaterialesXCombinacion
GO

CREATE TABLE MaterialesXCombinacion(
	ID INT IDENTITY(1,1) NOT NULL,
	Estilo INT NULL,
	Combinacion INT NULL,
	Pieza INT NULL,
        Estatus VARCHAR(25) NULL,
        Registro VARCHAR(25) NULL,
	PRIMARY KEY (ID)
);

GO

 
-- =========================================
-- Create table MaterialesXCombinacionDetalle
-- =========================================
USE LOBO;
GO

IF OBJECT_ID('dbo.MaterialesXCombinacionDetalle', 'U') IS NOT NULL
  DROP TABLE dbo.MaterialesXCombinacionDetalle
GO

CREATE TABLE MaterialesXCombinacionDetalle(
	ID INT IDENTITY(1,1) NOT NULL,
	MaterialXCombinacion INT NULL,
	Material INT NULL,
	Consumo FLOAT NULL,
	Tipo INT NULL,
        Estatus VARCHAR(25) NULL,
        Registro VARCHAR(25) NULL,
        Precio VARCHAR(25) NULL,
	PRIMARY KEY (ID)
);

GO
