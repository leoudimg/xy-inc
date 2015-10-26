<div class="span9" id="content">
	<!-- morris stacked chart -->
	<div class="row-fluid">
		<!-- block -->
		<div class="block">
			<div class="navbar navbar-inner block-header">
				<div class="muted pull-left">Atributo</div>
			</div>

			<form class="form-horizontal"
				action="<c:url value="/atributo/atualizar"/>" method="POST">
				<fieldset>
					<div class="block-content collapse in">
						<div class="span12">

							<legend>Adicionar Atributo</legend>

							<div class="control-group">
								<label class="control-label" for="focusedInput">Nome:</label>
								<div class="controls">
									<input class="input-small focused" id="atributo" type="text"
										name="atributo.nome" value="${atributo.nome}" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="focusedInput">Descrição:</label>
								<div class="controls">
									<input class="input-small focused" id="focusedInput"
										type="text" name="atributo.descricao"
										value="${atributo.descricao}" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="focusedInput">Modelo:</label>
								<div class="controls">
									<select class="form-control" name="atributo.modelo.id"
										value="${atributo.modelo.id }">
										<c:forEach items="${modeloList}" var="modelo">
											<option value="${modelo.id}">${modelo.nome}</option>
										</c:forEach>

									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="focusedInput">Tipo do
									Atributo:</label>
								<div class="controls">
									<select class="form-control"
										name="atributo.indicadorTipoAtributo"
										value="${atributo.indicadorTipoAtributo }">
										<option value="S">String</option>
										<option value="T">Text</option>
										<option value="D">Decimal</option>

									</select>
								</div>
							</div>



							<div class="form-actions">
								<input class="btn btn-primary" type="submit" value="Enviar" />
								<button class="btn" onclick="window.location.href='/atributo/'">Cancelar</button>

							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>