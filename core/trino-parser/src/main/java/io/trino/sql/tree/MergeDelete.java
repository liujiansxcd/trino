/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;

public class MergeDelete
        extends MergeCase
{
    public MergeDelete(Optional<Expression> expression)
    {
        this(Optional.empty(), expression);
    }

    public MergeDelete(NodeLocation location, Optional<Expression> expression)
    {
        super(Optional.of(location), expression);
    }

    public MergeDelete(Optional<NodeLocation> location, Optional<Expression> expression)
    {
        super(location, expression);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitMergeDelete(this, context);
    }

    @Override
    public List<Identifier> getSetColumns()
    {
        return ImmutableList.of();
    }

    @Override
    public List<Expression> getSetExpressions()
    {
        return ImmutableList.of();
    }

    @Override
    public List<? extends Node> getChildren()
    {
        return expression.map(ImmutableList::of).orElseGet(ImmutableList::of);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(expression);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        MergeDelete mergeDelete = (MergeDelete) obj;
        return Objects.equals(expression, mergeDelete.expression);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("expression", expression.orElse(null))
                .omitNullValues()
                .toString();
    }
}
