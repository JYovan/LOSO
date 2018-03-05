USE LOBO;
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- -----------------------------------------------------
-- procedure SP_USUARIOS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_USUARIOS' AND TYPE = 'P')
	DROP PROCEDURE SP_USUARIOS
GO
CREATE PROCEDURE SP_USUARIOS (@MAX INT)
	
AS
BEGIN
	SET NOCOUNT ON;
SELECT TOP (@MAX) U.ID AS ID, U.Usuario AS USUARIO, U.Tipo AS TIPO, U.Estatus AS ESTATUS,  U.Registro AS REGISTRO 
FROM Usuarios AS U 
WHERE U.Estatus IN ('ACTIVO','INACTIVO')
END
GO
-- -----------------------------------------------------
-- procedure SP_AGREGAR_USUARIO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_USUARIO' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_USUARIO
GO
CREATE  PROCEDURE SP_AGREGAR_USUARIO(@Usuario VARCHAR(45), @Contrasena VARCHAR(45), @Correo VARCHAR(45), @Registro VARCHAR(45))
AS
BEGIN
	SET NOCOUNT ON;
INSERT INTO usuarios ([Usuario],[Contrasena],[Correo],[Estatus],[Registro]) VALUES (@Usuario,@Contrasena,@Correo,'ACTIVO',@Registro);
END
GO

-- -----------------------------------------------------
-- procedure SP_USUARIO_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_USUARIO_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_USUARIO_X_ID
GO
CREATE PROCEDURE SP_USUARIO_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 U.ID AS ID, U.Usuario AS USUARIO, U.Contrasena AS CONTRASENA, U.Correo AS CORREO, U.Estatus AS ESTATUS, U.Tipo AS TIPO FROM Usuarios AS U
    WHERE U.ID = @IDX AND U.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_USUARIO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_USUARIO' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_USUARIO
GO
CREATE PROCEDURE SP_MODIFICAR_USUARIO(@IDX INT, @Usuario VARCHAR(45), @Contrasena VARCHAR(45), @Correo VARCHAR(45), @Tipo VARCHAR(65))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE usuarios
	SET [Usuario] = @Usuario, [Contrasena] =@Contrasena, [Correo] = @Correo ,[Tipo] = @Tipo 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_USUARIO
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_USUARIO' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_USUARIO
GO
CREATE PROCEDURE SP_ELIMINAR_USUARIO(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE usuarios
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_ACCEDER
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ACCEDER' AND TYPE = 'P')
	DROP PROCEDURE SP_ACCEDER
GO
CREATE PROCEDURE SP_ACCEDER(@Usuario VARCHAR(45),@Contrasena VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.Usuario AS USUARIO, U.Contrasena AS CONTRASENA FROM Usuarios AS U 
	WHERE U.Estatus IN('ACTIVO') AND  U.Usuario  = @Usuario AND U.Contrasena = @Contrasena;
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_MODULO
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_MODULO' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_MODULO
GO
CREATE  PROCEDURE SP_AGREGAR_MODULO(@Modulo VARCHAR(45), @Registro VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO Modulos ([Modulo],[Estatus],[Registro]) VALUES (@Modulo,'ACTIVO',@Registro);
END
GO

-- -----------------------------------------------------
-- procedure SP_MODULO_X_ID
-- -----------------------------------------------------


IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODULO_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_MODULO_X_ID
GO
CREATE PROCEDURE SP_MODULO_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 M.ID AS ID, M.MODULO AS MODULO, M.Estatus AS ESTATUS, M.Registro AS REGISTRO FROM Modulos AS M
    WHERE M.ID = @IDX AND M.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_MODULO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_MODULO' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_MODULO
GO
CREATE PROCEDURE SP_MODIFICAR_MODULO(@IDX INT, @Modulo VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Modulos
	SET [Modulo] = @Modulo 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_MODULO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_MODULO' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_MODULO
GO
CREATE PROCEDURE SP_ELIMINAR_MODULO(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Modulos
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_MODULOS
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODULOS' AND TYPE = 'P')
	DROP PROCEDURE SP_MODULOS
GO
CREATE PROCEDURE SP_MODULOS(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Modulo AS MODULO, M.Estatus AS ESTATUS, M.Registro AS REGISTRO FROM Modulos AS M WHERE M.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_PERMISOS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_PERMISOS' AND TYPE = 'P')
	DROP PROCEDURE SP_PERMISOS
GO
CREATE PROCEDURE SP_PERMISOS(@MAX INT)
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

-- -----------------------------------------------------
-- procedure SP_AGREGAR_PERMISO
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_PERMISO' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_PERMISO
GO
CREATE  PROCEDURE SP_AGREGAR_PERMISO(
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
-- -----------------------------------------------------
-- procedure SP_PERMISO_X_ID
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_PERMISO_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_PERMISO_X_ID
GO
CREATE PROCEDURE SP_PERMISO_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 P.ID AS ID, P.UsuarioT AS USUARIO,P.ModuloT AS MODULO, P.Ver, P.Crear, P.Modificar, P.Eliminar, P.Consultar, P.Reportes, P.Buscar, 
    P.Registro AS REGISTRO FROM Permisos AS P
    WHERE P.ID = @IDX AND P.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_PERMISO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_PERMISO' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_PERMISO
GO
CREATE PROCEDURE SP_MODIFICAR_PERMISO(@IDX INT, @Ver INT, 
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

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_PERMISO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_PERMISO' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_PERMISO
GO
CREATE PROCEDURE SP_ELIMINAR_PERMISO(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Permisos
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_MODULOS
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_MODULOS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_MODULOS
GO
CREATE PROCEDURE SP_OBTENER_MODULOS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT M.ID AS ID, M.Modulo AS MODULO FROM Modulos AS M WHERE M.Estatus IN('ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_USUARIOS
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_USUARIOS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_USUARIOS
GO
CREATE PROCEDURE SP_OBTENER_USUARIOS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.ID AS ID, U.Usuario AS USUARIO FROM Usuarios AS U WHERE U.Estatus IN('ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_CATALOGOS
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_CATALOGOS' AND TYPE = 'P')
	DROP PROCEDURE SP_CATALOGOS
GO
CREATE PROCEDURE SP_CATALOGOS(@MAX INT,@FIELD_ID VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) C.ID AS ID, C.IValue AS Orden, C.SValue AS Nombre,C.Valor_Text AS Descripcion, C.Valor_Num AS Valor   FROM Catalogos AS C WHERE C.FieldId = @FIELD_ID AND C.Estatus IN('ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_CATALOGO
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_CATALOGO' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_CATALOGO
GO
CREATE  PROCEDURE SP_AGREGAR_CATALOGO(@FieldId VARCHAR(45),@IValue INT, @SValue VARCHAR(50),@Special VARCHAR(50),@Valor_Num FLOAT,@Valor_Text VARCHAR(85))
AS
BEGIN
SET NOCOUNT ON;
	INSERT INTO catalogos ([FieldId],[IValue],[SValue],[Special],[Valor_Num],[Valor_Text],[Estatus]) VALUES (@FieldId,@IValue,@SValue,@Special,@Valor_Num,@Valor_Text,'ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_CATALOGO_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_CATALOGO_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_CATALOGO_X_ID
GO
CREATE PROCEDURE SP_CATALOGO_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 C.IValue AS Orden, C.SValue AS Nombre,  C.Valor_Text AS Descripcion, C.Valor_Num AS Valor, C.Special AS Special ,C.Estatus AS Estatus FROM Catalogos AS C
    WHERE C.ID = @IDX  ;
END
GO

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_CATALOGO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_CATALOGO' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_CATALOGO
GO
CREATE PROCEDURE SP_MODIFICAR_CATALOGO(@IDX INT,@IValue INT, @SValue VARCHAR(50), @Valor_Num FLOAT,@Valor_Text VARCHAR(85),@Special VARCHAR(50),@Estatus VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE catalogos
	SET [IValue] = @IValue,[SValue] = @SValue, [Valor_Num] =@Valor_Num, [Valor_Text] = @Valor_Text ,[Special] = @Special , [Estatus] = @Estatus 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_CATALOGO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_CATALOGO' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_CATALOGO
GO
CREATE PROCEDURE SP_ELIMINAR_CATALOGO(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE catalogos
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_BUSCAR_CATALOGO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_BUSCAR_CATALOGO' AND TYPE = 'P')
	DROP PROCEDURE SP_BUSCAR_CATALOGO
GO
CREATE PROCEDURE SP_BUSCAR_CATALOGO(@Valor_Text VARCHAR(85),@SValue VARCHAR(50))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.SValue AS Nombre, C.Valor_Num AS Valor,  C.Valor_Text AS Descripcion FROM Catalogos AS C WHERE C.Estatus IN('ACTIVO')
	AND C.Valor_Text LIKE CONCAT('%',@Valor_Text,'%') AND C.SValue LIKE CONCAT('%',@SValue,'%');
END
GO

-- -----------------------------------------------------
-- procedure SP_LINEAS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_LINEAS' AND TYPE = 'P')
	DROP PROCEDURE SP_LINEAS
GO
CREATE PROCEDURE SP_LINEAS(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX) L.ID AS ID, L.Clave AS CLAVE, L.Descripcion AS DESCRIPCION, L.Ano AS ANO, L.Estatus AS ESTATUS FROM Lineas AS L WHERE L.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_LINEA
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_LINEA' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_LINEA
GO
CREATE  PROCEDURE SP_AGREGAR_LINEA(
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

-- -----------------------------------------------------
-- procedure SP_LINEA_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_LINEA_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_LINEA_X_ID
GO
CREATE PROCEDURE SP_LINEA_X_ID(@IDX INT)
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

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_LINEA
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_LINEA' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_LINEA
GO
CREATE PROCEDURE SP_MODIFICAR_LINEA(@IDX INT, @Clave VARCHAR(45), @Descripcion VARCHAR(90), @TipoEstiloCat INT,@Ano VARCHAR(45), @TemporadaCat INT, @Estatus VARCHAR(45))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Lineas
	SET [Clave] = @Clave, [Descripcion] = @Descripcion, [TipoEstiloCat] = @TipoEstiloCat, [Ano] = @Ano,[TemporadaCat] = @TemporadaCat,[Estatus] = @Estatus
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_LINEA
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_LINEA' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_LINEA
GO
CREATE PROCEDURE SP_ELIMINAR_LINEA(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Lineas
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_BUSCAR_LINEA
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_BUSCAR_LINEA' AND TYPE = 'P')
	DROP PROCEDURE SP_BUSCAR_LINEA
GO
CREATE PROCEDURE SP_BUSCAR_LINEA(@MAX INT, @Clave VARCHAR(45),@Descripcion VARCHAR(90))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) L.ID AS ID, L.Clave AS CLAVE, L.Descripcion AS DESCRIPCION, L.Ano AS ANO, L.Estatus AS ESTATUS  FROM Lineas AS L 
	WHERE L.Clave LIKE CONCAT('%',@Clave,'%') AND L.Descripcion LIKE CONCAT('%',@Descripcion,'%');
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_TEMPORADAS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_TEMPORADAS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_TEMPORADAS
GO
CREATE PROCEDURE SP_OBTENER_TEMPORADAS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS TEMPORADA FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'TEMPORADAS';
END
GO

-- -----------------------------------------------------
-- procedure SP_ESTILOS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ESTILOS' AND TYPE = 'P')
	DROP PROCEDURE SP_ESTILOS
GO
CREATE PROCEDURE SP_ESTILOS(@MAX INT)
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

-- -----------------------------------------------------
-- procedure SP_AGREGAR_ESTILO
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_ESTILO' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_ESTILO
GO
CREATE  PROCEDURE SP_AGREGAR_ESTILO(
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

-- -----------------------------------------------------
-- procedure SP_OBTENER_LINEAS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_LINEAS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_LINEAS
GO
CREATE PROCEDURE SP_OBTENER_LINEAS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT L.ID AS ID, L.Descripcion AS LINEA FROM Lineas AS L WHERE L.Estatus IN('ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_FAMILIAS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_FAMILIAS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_FAMILIAS
GO
CREATE PROCEDURE SP_OBTENER_FAMILIAS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT F.ID AS ID, F.SValue AS FAMILIA FROM CATALOGOS AS F WHERE F.Estatus IN('ACTIVO') AND F.FieldId LIKE 'FAMILIAS';
END
GO
-- -----------------------------------------------------
-- procedure SP_OBTENER_SERIES
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_SERIES' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_SERIES
GO
CREATE PROCEDURE SP_OBTENER_SERIES
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS SERIE FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'SERIES';
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_HORMAS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_HORMAS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_HORMAS
GO
CREATE PROCEDURE SP_OBTENER_HORMAS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS HORMA FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'HORMAS';
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_MAQUILAS
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_MAQUILAS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_MAQUILAS
GO
CREATE PROCEDURE SP_OBTENER_MAQUILAS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.Nombre AS MAQUILA FROM Maquilas AS C WHERE C.Estatus IN('ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_TIPOS_ESTILO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_TIPOS_ESTILO' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_TIPOS_ESTILO
GO
CREATE PROCEDURE SP_OBTENER_TIPOS_ESTILO
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.SValue AS TIPO FROM CATALOGOS AS C WHERE C.Estatus IN('ACTIVO') AND C.FieldId LIKE 'TIPOS ESTILO';
END
GO

-- -----------------------------------------------------
-- procedure SP_ESTILO_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ESTILO_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_ESTILO_X_ID
GO
CREATE PROCEDURE SP_ESTILO_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
SELECT 
	E.ID, L.Descripcion, E.Clave, E.Descripcion, F.SValue AS FAMILIA, 
	S.SValue AS SERIE, H.SValue AS HORMA, E.Genero, E.Foto, E.Estatus, 
	E.Desperdicio, E.Liberado, E.Herramental, M.Nombre  AS MAQUILA, E.Notas, 
	E.Ano, T.SValue AS TEMPORADA, E.PuntoCentral, TE.SValue  AS TIPO_ESTILO, E.MaquilaPlantilla, 
	E.TipoDeConstruccion, E.Registro AS REGISTRO 
    FROM Estilos AS E 
	LEFT JOIN Maquilas AS M ON E.Maquila = M.ID  
    LEFT JOIN Lineas AS L ON E.Linea = L.ID 
    LEFT JOIN Catalogos AS F ON E.Familia = F.ID AND F.FieldId LIKE 'FAMILIAS'
    LEFT JOIN Catalogos AS S ON E.Serie = S.ID  AND S.FieldId LIKE 'SERIES'
    LEFT JOIN Catalogos AS H ON E.Horma = H.ID  AND H.FieldId LIKE 'HORMAS'
    LEFT JOIN Catalogos AS T ON E.Temporada = T.ID  AND T.FieldId LIKE 'TEMPORADAS'
    LEFT JOIN Catalogos AS TE ON E.Tipo = TE.ID  AND TE.FieldId LIKE 'TIPOS ESTILO'
    WHERE E.ID = @IDX AND E.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_MAQUILAS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MAQUILAS' AND TYPE = 'P')
	DROP PROCEDURE SP_MAQUILAS
GO
CREATE PROCEDURE SP_MAQUILAS(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Clave AS CLAVE, M.Nombre AS NOMBRE,M.Estatus AS ESTATUS FROM Maquilas AS M WHERE M.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_MAQUILA
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_MAQUILA' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_MAQUILA
GO
CREATE  PROCEDURE SP_AGREGAR_MAQUILA(
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

-- -----------------------------------------------------
-- procedure SP_MAQUILA_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MAQUILA_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_MAQUILA_X_ID
GO
CREATE PROCEDURE SP_MAQUILA_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP 1 M.ID AS ID, M.Clave AS CLAVE, M.NOMBRE AS NOMBRE, M.Direccion  AS DIRECCION, M.Telefono AS TELEFONO, M.Contacto AS CONTACTO,
		M.Estatus AS ESTATUS
    FROM MAQUILAS AS M
    WHERE M.ID = @IDX AND M.Estatus IN('ACTIVO','INACTIVO') ;
END
GO

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_MAQUILA
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_MAQUILA' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_MAQUILA
GO
CREATE PROCEDURE SP_MODIFICAR_MAQUILA(
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

-- -----------------------------------------------------
-- procedure SP_BUSCAR_MAQUILA
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_BUSCAR_MAQUILA' AND TYPE = 'P')
	DROP PROCEDURE SP_BUSCAR_MAQUILA
GO
CREATE PROCEDURE SP_BUSCAR_MAQUILA(@MAX INT, @Clave VARCHAR(45),@Nombre VARCHAR(80))
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Clave AS CLAVE, M.Nombre AS NOMBRE,M.Estatus AS ESTATUS FROM Maquilas AS M 
	WHERE M.Clave LIKE CONCAT('%',@Clave,'%') AND M.Nombre LIKE CONCAT('%',@Nombre,'%') ;
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_MAQUILA
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_MAQUILA' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_MAQUILA
GO
CREATE PROCEDURE SP_ELIMINAR_MAQUILA(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE maquilas
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_ESTILO
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_ESTILO' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_ESTILO
GO
CREATE  PROCEDURE SP_MODIFICAR_ESTILO(@IDX INT,
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

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_ESTILO
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_ESTILO' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_ESTILO
GO
CREATE PROCEDURE SP_ELIMINAR_ESTILO(@IDX INT)
AS
BEGIN
SET NOCOUNT ON;
	UPDATE estilos
		SET [Estatus] = 'INACTIVO' WHERE [ID] = @IDX;
END
GO



-- -----------------------------------------------------
-- procedure SP_COMBINACIONES
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_COMBINACIONES' AND TYPE = 'P')
	DROP PROCEDURE SP_COMBINACIONES
GO
CREATE PROCEDURE SP_COMBINACIONES(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX) C.ID AS ID, C.Clave AS CLAVE, C.Descripcion AS DESCRIPCION, C.Estatus AS ESTATUS FROM Combinaciones AS C WHERE C.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_COMBINACION
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_COMBINACION' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_COMBINACION
GO
CREATE  PROCEDURE SP_AGREGAR_COMBINACION(
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

-- -----------------------------------------------------
-- procedure SP_COMBINACION_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_COMBINACION_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_COMBINACION_X_ID
GO
CREATE PROCEDURE SP_COMBINACION_X_ID(@IDX INT)
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

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_COMBINACION
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_COMBINACION' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_COMBINACION
GO
CREATE PROCEDURE SP_MODIFICAR_COMBINACION(@IDX INT, @Clave VARCHAR(15), @Descripcion VARCHAR(100), @Linea INT,@Estilo INT, @Estatus VARCHAR(25))
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Combinaciones
	SET [Clave] = @Clave, [Descripcion] = @Descripcion, [Linea] = @Linea,[Estilo] = @Estilo,[Estatus] = @Estatus
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_COMBINACION
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_COMBINACION' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_COMBINACION
GO
CREATE PROCEDURE SP_ELIMINAR_COMBINACION(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Combinaciones
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_ESTILOS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_ESTILOS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_ESTILOS
GO
CREATE PROCEDURE SP_OBTENER_ESTILOS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT E.ID AS ID, E.Descripcion AS LINEA FROM Estilos AS E WHERE E.Estatus IN('ACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_MATERIALES
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MATERIALES' AND TYPE = 'P')
	DROP PROCEDURE SP_MATERIALES
GO
CREATE PROCEDURE SP_MATERIALES(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP(@MAX) M.ID AS ID, M.Descripcion AS DESCRIPCION, M.Estatus AS ESTATUS
	FROM Materiales AS M
    WHERE M.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_MATERIAL
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_MATERIAL' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_MATERIAL
GO
CREATE PROCEDURE SP_ELIMINAR_MATERIAL(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Material
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO



-- -----------------------------------------------------
-- procedure SP_OBTENER_UNIDADES
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_UNIDADES' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_UNIDADES
GO
CREATE PROCEDURE SP_OBTENER_UNIDADES
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.ID AS ID, U.SValue AS UNIDAD FROM CATALOGOS AS U WHERE U.Estatus IN('ACTIVO') AND U.FieldId LIKE 'UNIDADES';
END
GO


-- -----------------------------------------------------
-- procedure SP_MATERIAL_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MATERIAL_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_MATERIAL_X_ID
GO
CREATE PROCEDURE SP_MATERIAL_X_ID(@IDX INT)
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


-- -----------------------------------------------------
-- procedure SP_AGREGAR_MATERIAL
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_MATERIAL' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_MATERIAL
GO
CREATE  PROCEDURE SP_AGREGAR_MATERIAL(@Material VARCHAR(45), 
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

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_MATERIAL
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_MATERIAL' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_MATERIAL
GO
CREATE  PROCEDURE SP_MODIFICAR_MATERIAL(@ID INT, @Material VARCHAR(45), 
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

-- -----------------------------------------------------
-- procedure SP_FRACCIONES
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_FRACCIONES' AND TYPE = 'P')
	DROP PROCEDURE SP_FRACCIONES
GO
CREATE PROCEDURE SP_FRACCIONES(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
	SELECT TOP (@MAX) C.ID AS ID, C.Clave AS CLAVE, C.Descripcion AS DESCRIPCION, C.Estatus AS ESTATUS FROM Fracciones AS C WHERE C.Estatus IN('ACTIVO','INACTIVO');
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_FRACCION
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_FRACCION' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_FRACCION
GO
CREATE  PROCEDURE SP_AGREGAR_FRACCION(
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

-- -----------------------------------------------------
-- procedure SP_FRACCION_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_FRACCION_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_FRACCION_X_ID
GO
CREATE PROCEDURE SP_FRACCION_X_ID(@IDX INT)
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

-- -----------------------------------------------------
-- procedure SP_MODIFICAR_FRACCION
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MODIFICAR_FRACCION' AND TYPE = 'P')
	DROP PROCEDURE SP_MODIFICAR_FRACCION
GO
CREATE PROCEDURE SP_MODIFICAR_FRACCION(
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

-- -----------------------------------------------------
-- procedure SP_ELIMINAR_FRACCION
-- -----------------------------------------------------

IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_ELIMINAR_FRACCION' AND TYPE = 'P')
	DROP PROCEDURE SP_ELIMINAR_FRACCION
GO
CREATE PROCEDURE SP_ELIMINAR_FRACCION(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 
	UPDATE Fracciones
	SET [Estatus] = 'INACTIVO' 
	WHERE [ID] = @IDX; 
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_DEPARTAMENTOS
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_DEPARTAMENTOS' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_DEPARTAMENTOS
GO
CREATE PROCEDURE SP_OBTENER_DEPARTAMENTOS
AS
BEGIN
SET NOCOUNT ON; 
	SELECT D.ID AS ID, D.SValue AS DEPARTAMENTOS FROM CATALOGOS AS D WHERE D.Estatus IN('ACTIVO') AND D.FieldId LIKE 'DEPARTAMENTOS';
END
GO

-- -----------------------------------------------------
-- procedure SP_OBTENER_ESTILOS_MXC
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_ESTILOS_MXC' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_ESTILOS_MXC
GO
CREATE PROCEDURE SP_OBTENER_ESTILOS_MXC
AS
BEGIN
SET NOCOUNT ON; 
	SELECT E.ID AS ID, E.Descripcion AS LINEA FROM Estilos AS E WHERE E.Estatus IN('ACTIVO');
END
GO
-- -----------------------------------------------------
-- procedure SP_MATERIALES_X_COMBINACION
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MATERIALES_X_COMBINACION' AND TYPE = 'P')
	DROP PROCEDURE SP_MATERIALES_X_COMBINACION
GO
CREATE PROCEDURE SP_MATERIALES_X_COMBINACION(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 

	SELECT [ID]
      ,[Estilo] AS ESTILO
      ,[Combinacion] AS COMBINACION
      ,[Pieza] AS PIEZA FROM [MaterialesXCombinacion] AS MXC WHERE MXC.Estatus IN('ACTIVO');
END
GO
-- -----------------------------------------------------
-- procedure SP_MATERIALES_X_COMBINACION_X_ID
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_MATERIALES_X_COMBINACION_X_ID' AND TYPE = 'P')
	DROP PROCEDURE SP_MATERIALES_X_COMBINACION_X_ID
GO
CREATE PROCEDURE SP_MATERIALES_X_COMBINACION_X_ID(@IDX INT)
AS
BEGIN
SET NOCOUNT ON; 

	SELECT [ID]
      ,[Estilo] AS ESTILO
      ,[Combinacion] AS COMBINACION
      ,[Pieza] AS PIEZA FROM [MaterialesXCombinacion] AS MXC WHERE MXC.Estatus IN('ACTIVO') AND MXC.ID = @ID;
END
GO
-- -----------------------------------------------------
-- procedure SP_OBTENER_COMBINACIONES_MXC
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_COMBINACIONES_MXC' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_COMBINACIONES_MXC
GO
CREATE PROCEDURE SP_OBTENER_COMBINACIONES_MXC
AS
BEGIN
SET NOCOUNT ON; 
	SELECT C.ID AS ID, C.Descripcion AS COMBINACION FROM Combinaciones AS C WHERE C.Estatus IN('ACTIVO');
END
GO
-- -----------------------------------------------------
-- procedure SP_OBTENER_PIEZAS_MXC
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_PIEZAS_MXC' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_PIEZAS_MXC
GO
CREATE PROCEDURE SP_OBTENER_PIEZAS_MXC
AS
BEGIN
SET NOCOUNT ON; 
	SELECT U.ID AS ID, U.SValue AS UNIDAD FROM CATALOGOS AS U WHERE U.Estatus IN('ACTIVO') AND U.FieldId LIKE 'PIEZAS';
END
GO 
-- -----------------------------------------------------
-- procedure SP_AGREGAR_MATERIALES_X_COMBINACION
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_MATERIALES_X_COMBINACION' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_MATERIALES_X_COMBINACION
GO
CREATE PROCEDURE SP_AGREGAR_MATERIALES_X_COMBINACION(@Estilo INT, @Combinacion INT, @Pieza INT,@Registro VARCHAR(25))
AS
BEGIN
SET NOCOUNT ON; 
INSERT INTO [dbo].[MaterialesXCombinacion]
           ([Estilo],[Combinacion],[Pieza],[Estatus],[Registro])
     VALUES
           (@Estilo
           ,@Combinacion
           ,0
           ,'ACTIVO',@Registro);
		   DECLARE @id INT;
      SELECT SCOPE_IDENTITY() AS ULTIMOID;
END
GO

-- -----------------------------------------------------
-- procedure SP_AGREGAR_MATERIALES_X_COMBINACION_DETALLE
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_AGREGAR_MATERIALES_X_COMBINACION_DETALLE' AND TYPE = 'P')
	DROP PROCEDURE SP_AGREGAR_MATERIALES_X_COMBINACION_DETALLE
GO
CREATE PROCEDURE SP_AGREGAR_MATERIALES_X_COMBINACION_DETALLE(@IDX INT, @Material INT, @Consumo FLOAT, @Tipo INT,@Registro VARCHAR(25))
AS
BEGIN
SET NOCOUNT ON;   
INSERT INTO [dbo].[MaterialesXCombinacionDetalle]
           ([MaterialXCombinacion],[Material],[Consumo],[Tipo],[Estatus],[Registro])
     VALUES
           (@IDX
           ,@Material
           ,@Consumo
           ,@Tipo
           ,'ACTIVO',@Registro );

END
GO


-- -----------------------------------------------------
-- procedure SP_OBTENER_MATERIALES_MXC
-- -----------------------------------------------------
IF EXISTS (	SELECT name FROM sysobjects WHERE  name = 'SP_OBTENER_MATERIALES_MXC' AND TYPE = 'P')
	DROP PROCEDURE SP_OBTENER_MATERIALES_MXC
GO
CREATE PROCEDURE SP_OBTENER_MATERIALES_MXC(@MAX INT)
AS
BEGIN
SET NOCOUNT ON; 
    SELECT M.[ID] ,M.[Material] AS MATERIAL, M.[UnidadConsumo] AS "U.M", M.[PrecioLista] AS PRECIO FROM [Materiales] AS M WHERE M.Estatus IN('ACTIVO');
END
GO