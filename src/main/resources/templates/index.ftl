<!dcotype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Contratación de Monitores</title>

    <link rel="stylesheet" href="/lib/jquery-ui/jquery-ui.css">
    <link rel="stylesheet" href="/lib/select2/select2.css">
    <link rel="stylesheet" href="/lib/dropzone/dropzone.css">
    <link rel="stylesheet" href="/lib/jquery-toggles/toggles-full.css">
    <link rel="stylesheet" href="/lib/fontawesome/css/font-awesome.css">
    <link rel="stylesheet" href="/lib/timepicker/jquery.timepicker.css">
    <link rel="stylesheet" href="/lib/bootstrapcolorpicker/css/bootstrap-colorpicker.css">

    <link rel="stylesheet" href="/css/quirk.css">
    <script src="/lib/modernizr/modernizr.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="../lib/html5shiv/html5shiv.js"></script>
    <script src="../lib/respond/respond.src.js"></script>
    <![endif]-->
  </head>

  <body>
    <header>
      <div class="headerpanel">
        <div class="logopanel">
          <h2><a href="index.html">Contratación</a></h2>
        </div>
        <div class="headerbar">
          <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
        </div>
      </div>
    </header>

    <section>
      <div class="leftpanel">
        <div class="leftpanelinner">
          <div class="media leftpanel-profile">
            <div class="media-left">
              <a href="#">
                <img src="../images/photos/loggeduser.png" alt="" class="media-object img-circle">
              </a>
            </div>
            <div class="media-body">
              <h4 class="media-heading">Contratación</h4>
              <span>Monitores</span>
            </div>
          </div>
          <div class="tab-content">
            <div class="tab-pane active" id="mainmenu">
              <h5 class="sidebar-title">Menú</h5>
              <ul class="nav nav-pills nav-stacked nav-quirk">
                <li class="active"><a href="/"><i class="fa fa-home"></i> <span>Asignar</span></a></li>
                <li class="active"><a href="/monitorias"><i class="fa fa-home"></i> <span>Monitorias</span></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="mainpanel">
        <div class="contentpanel">
          <div class="row">
            <div class="col-md-12">
              <div class="panel">
                  <div class="panel-heading nopaddingbottom">
                    <h4 class="panel-title">Asignación de Monitorias</h4>
                    <p>Por favor selecciona el estudiante y el curso que desea asignarle la monitoria</p>
                  </div>
                  <div class="panel-body">
                    <hr>
                    <form id="basicForm" class="form-horizontal" action="/verificar" method="post">
                      <div class="form-group">
                        <label class="col-sm-3 control-label">Estudiante <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                          <select id="select1" name="estudiante" class="form-control" style="width: 100%" data-placeholder="Estudiante" required>
                            <option value="">&nbsp;</option>
	                    	<#list estudiantes as estudiante>
								<option value="${estudiante.getId_estudiante()}">${estudiante.getCarnet()} -- ${estudiante.getNombres()} ${estudiante.getApellidos()}</option>
							</#list>
                          </select>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="col-sm-3 control-label">Curso <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                          <select id="select1" name="curso" class="form-control" style="width: 100%" data-placeholder="Curso" required>
                            <option value="">&nbsp;</option>
                            <#list cursos as curso>
								<option value="${curso.getId_curso()}">${curso.getCodigo()} -- ${curso.getCurso()}</option>
							</#list>
                          </select>
                        </div>
                      </div>
                      <hr>
                      <div class="row">
                        <div class="col-sm-9 col-sm-offset-3">
                          <button class="btn btn-success btn-quirk btn-wide mr5">Asignar</button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <#if error??>
	                  <div class="alert alert-danger">
	                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
	                    <strong>${error}!</strong>
	                  </div>
				  </#if>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <script src="/lib/jquery/jquery.js"></script>
    <script src="/lib/jquery-ui/jquery-ui.js"></script>
    <script src="/lib/bootstrap/js/bootstrap.js"></script>
    <script src="/lib/jquery-autosize/autosize.js"></script>
    <script src="/lib/select2/select2.js"></script>
    <script src="/lib/jquery-toggles/toggles.js"></script>
    <script src="/lib/jquery-maskedinput/jquery.maskedinput.js"></script>
    <script src="/lib/timepicker/jquery.timepicker.js"></script>
    <script src="/lib/dropzone/dropzone.js"></script>
    <script src="/lib/bootstrapcolorpicker/js/bootstrap-colorpicker.js"></script>

    <script src="/js/quirk.js"></script>

    <script>
    $(function() {
      autosize($('#autosize'));
      $('#select1, #select2, #select3').select2();
    });
    </script>
  </body>
</html>