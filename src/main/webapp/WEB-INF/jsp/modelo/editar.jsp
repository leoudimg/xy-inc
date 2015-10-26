<div class="span9" id="content">
	<!-- morris stacked chart -->
	<div class="row-fluid">
		<!-- block -->
		<div class="block">
			<div class="navbar navbar-inner block-header">
				<div class="muted pull-left">Modelo</div>
			</div>

			<form class="form-horizontal"
				action="<c:url value="/modelo/atualizar"/>" method="POST">
				<fieldset>
					<div class="block-content collapse in">
						<div class="span12">

							<legend>Adicionar Modelo</legend>

							<input type="hidden" name="modelo.id" value="${modelo.id}">

							<div class="control-group">
								<label class="control-label" for="focusedInput">Nome:</label>
								<div class="controls">
									<input class="input-small focused" id="modelo" type="text"
										name="modelo.nome" value="${modelo.nome}" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="focusedInput">Descrição:</label>
								<div class="controls">
									<input class="input-small focused" id="focusedInput"
										type="text" name="modelo.descricao"
										value="${modelo.descricao}" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="focusedInput">URL
									REST:</label>
								<div class="controls">
									<input class="input-small focused" id="focusedInput"
										type="text" name="modelo.link" value="${modelo.link}" />
								</div>
							</div>



							<div class="form-actions">
								<input class="btn btn-primary" type="submit" value="Enviar" />
								<button class="btn" onclick="window.location.href='/modelo/'">Cancelar</button>

							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>