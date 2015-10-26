
<div class="span9" id="content">

	<div class="row-fluid">
		<!-- block -->
		<div class="block">
			<div class="navbar navbar-inner block-header">
				<div class="muted pull-left">Lista de Atributos</div>
				<button class="button-grid"
					onclick="window.location.href='/atributo/novo'">Novo</button>
			</div>
			<div class="block-content collapse in">
				<div class="span12">
					<table cellpadding="0" cellspacing="0" border="0"
						class="table table-striped table-bordered" id="example">
						<thead>
							<tr>
								<th>Identificador</th>
								<th>Nome</th>
								<th>Descrição</th>
								<th>Modelo</th>
								<th>Tipo</th>

								<th>Editar</th>
								<th>Remover</th>
							</tr>
						</thead>
						<tbody class="odd gradeX">
							<c:forEach items="${atributoList}" var="atributo">
								<tr>
									<td>${atributo.id }</td>
									<td>${atributo.nome }</td>
									<td>${atributo.descricao }</td>
									<td>${atributo.modelo.nome}</td>
									<c:if test="${atributo.indicadorTipoAtributo eq 'S'}">
										<td>String</td>
									</c:if>
									<c:if test="${atributo.indicadorTipoAtributo eq 'T'}">
										<td>Texto</td>
									</c:if>
									<c:if test="${atributo.indicadorTipoAtributo eq 'D'}">
										<td>Decimal</td>
									</c:if>
									<td><a
										href="<c:url value="/atributo/editar/${atributo.id}"/>">Editar</a></td>
									<td><a
										href="<c:url value="/atributo/excluir/${atributo.id}"/>">Excluir</a>

									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

